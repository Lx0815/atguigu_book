package book.listener;

import book.ioc.BeanFactory;
import book.ioc.impl.BeanFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.nio.file.Paths;

@WebListener
public class ContextLoadListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger("ContextLoadListener 日志记录器");

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.info("开始初始化 BeanFactory");
        String contextConfigLocation = sce.getServletContext().getInitParameter("contextConfigLocation");
        BeanFactory beanFactory;
        if (contextConfigLocation != null) {
            beanFactory = BeanFactoryImpl.newInstance(Paths.get(contextConfigLocation));
        } else {
            beanFactory = BeanFactoryImpl.newInstance();
        }
        sce.getServletContext().setAttribute("beanFactory", beanFactory);
        log.info("BeanFactory 初始化成功");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
