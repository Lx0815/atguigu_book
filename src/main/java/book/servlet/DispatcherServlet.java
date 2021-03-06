package book.servlet;
/**
 * @author: Ding
 * @date: 2022/6/29
 * @description: 中央控制器
 * @modify:
 */

import book.ioc.BeanFactory;
import book.ioc.impl.BeanFactoryImpl;
import book.utils.LoggerUtils;
import book.utils.TransactionUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = this.getServletContext();
        BeanFactory beanFactory = (BeanFactory) servletContext.getAttribute("beanFactory");
        if (beanFactory == null) {
            beanFactory = BeanFactoryImpl.newInstance();
            servletContext.setAttribute("beanFactory", beanFactory);
            LoggerUtils.logInfo("DispatcherServlet#init()", "beanFactory 在 DispatcherServlet 中成功初始化");
        }
        this.beanFactory = beanFactory;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operate = request.getParameter("operate");
        if (operate == null || "".equals(operate)) {
            super.processTemplate("user/login", request, response);
            return;
        }

        String servletPath = request.getServletPath();
        Matcher matcher = Pattern.compile("\\w+(?=(\\.do))").matcher(servletPath);
        if (matcher.find()) {
            servletPath = matcher.group(0);
        }
        Object bean = beanFactory.getBean(servletPath + "Controller");
        LoggerUtils.logInfo("收到用户请求: " + servletPath + " <==> " + operate);

        if (bean == null) {
            throw new RuntimeException(servletPath + " 对象不存在");
        }

        try {
            Class<?> beanClass = bean.getClass();
            Method[] beanClassDeclaredMethods = beanClass.getDeclaredMethods();
            for (Method beanClassDeclaredMethod : beanClassDeclaredMethods) {
                if (beanClassDeclaredMethod.getName().equals(operate)) {
                    Parameter[] parameters = beanClassDeclaredMethod.getParameters();
                    Object[] params = new Object[parameters.length];
                    // 获取参数
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        if ("request".equals(parameterName)) {
                            params[i] = request;
                        } else if ("response".equals(parameterName)) {
                            params[i] = response;
                        } else if ("session".equals(parameterName)) {
                            params[i] = request.getSession();
                        } else {
                            params[i] = request.getParameter(parameterName);
                        }
                    }

                    // 开启事务
                    TransactionUtils.beginTransaction();

                    // 方法调用
                    beanClassDeclaredMethod.setAccessible(true);
                    Object resultObj;
                    if (params.length == 0) {
                        resultObj = beanClassDeclaredMethod.invoke(bean);
                    } else {
                        resultObj = beanClassDeclaredMethod.invoke(bean, params);
                    }
                    String resultStr = null;

                    // 返回值校验
                    if (resultObj instanceof String) {
                        resultStr = (String) resultObj;
                    } else if (resultObj == null) {
                        return;
                    } else {
                        throw new RuntimeException("Controller 层的方法没有以 String 为返回值");
                    }

                    // 视图处理
                    if (resultStr.startsWith("thymeleaf:")) {
                        super.processTemplate(resultStr.substring(10), request, response);
                    } else if (resultStr.startsWith("script:")) {
                        response.getWriter().write(resultStr.substring(7));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("此Method对象正在执行 Java 语言访问控制并且底层方法不可访问。");
        } catch (InvocationTargetException e) {
//            throw new RuntimeException("方法是实例方法并且指定的对象参数不是声明底层方法（或其子类或实现者）的类或接口的实例；如果实际参数和形式参数的数量不同；如果原始参数的展开转换失败；或者，如果在可能的展开之后，参数值不能通过方法调用转换转换为相应的形式参数类型。");
            e.printStackTrace();
        } catch (Exception e) {
            TransactionUtils.rollback();
        } finally {
            TransactionUtils.commit();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
