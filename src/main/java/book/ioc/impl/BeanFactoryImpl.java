package book.ioc.impl;

import book.ioc.BeanFactory;
import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: bean 对象工厂
 * @modify:
 */

public class BeanFactoryImpl implements BeanFactory {

    /**
     * 描述 bean 对象之间依赖关系的配置文件
     */
    private static final Path config = Paths.get("applicationContext.xml");

    /**
     * ioc 容器，存放 bean 对象
     */
    private static final HashMap<String, Object> beanMap = new HashMap<>();

    /**
     * 单例对象
     */
    private static BeanFactoryImpl beanFactory;

    private BeanFactoryImpl(Path config) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(config.toString());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            documentBuilderFactory.setValidating(true);

            documentBuilderFactory.setIgnoringElementContentWhitespace(true);

            // 创建 DocumentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();


            documentBuilder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {

                }

                @Override
                public void error(SAXParseException exception) throws SAXException {

                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {

                }
            });

            documentBuilder.setErrorHandler(new DefaultErrorHandler());

            Document document = documentBuilder.parse(inputStream);

            NodeList beanList = document.getElementsByTagName("bean");

            // 实例化 bean 对象并放入容器
            for (int i = 0; i < beanList.getLength(); i++) {
                Node node = beanList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String beanId = element.getAttribute("id");
                    String classPathStr = element.getAttribute("class");

                    Class<?> aClass = Class.forName(classPathStr);
                    Object beanObj = aClass.newInstance();

                    beanMap.put(beanId, beanObj);
                }
            }

            // 注入依赖
            for (int i = 0; i < beanList.getLength(); i++) {
                Node node = beanList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String beanId = element.getAttribute("id");

                    NodeList childNodeList = element.getChildNodes();
                    for (int j = 0; j < childNodeList.getLength(); j++) {
                        Node childNode = childNodeList.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNode;

                            String filedName = childElement.getAttribute("name");
                            String refId = childElement.getAttribute("ref");

                            Object beanObj = beanMap.get(beanId);
                            Object propertyObj = beanMap.get(refId);
                            Field field = beanObj.getClass().getDeclaredField(filedName);
                            field.setAccessible(true);
                            field.set(beanObj, propertyObj);

                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException("无法创建满足请求配置的 DocumentBuilder。");
        } catch (IOException e) {
            throw new RuntimeException("ioc 配置文件异常");
        } catch (SAXException e) {
            throw new RuntimeException("发生文档解析错误");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("bean 类路径错误");
        } catch (InstantiationException e) {
            throw new RuntimeException("如果此类表示抽象Class 、接口、数组类、原始类型或 void；或者如果该类没有空构造函数；或者如果实例化由于某种其他原因而失败。");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("如果类或其空构造函数不可访问。");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("字段未找到");
        }


    }

    /**
     * 单例模式
     * @return 返回 bean 对象工厂
     */
    public static BeanFactory newInstance() {
        return newInstance(config);
    }

    /**
     * 单例模式
     * @param config 自定义的配置文件
     * @return 返回 bean 对象工厂
     */
    public static BeanFactory newInstance(Path config) {
        if (beanFactory == null) {
            beanFactory = new BeanFactoryImpl(config);
        }
        return beanFactory;
    }

    @Override
    public Object getBean(String beanId) {
        return beanMap.get(beanId);
    }
}
