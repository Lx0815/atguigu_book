package book.filter;

import book.utils.LoggerUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(filterName = "SessionFilter", urlPatterns = {"*.do", "*.html"},
            initParams = @WebInitParam( name = "whiteListStr" ,
                                        value = "/book/page.do?operate=toLogin," +
                                                "/book/page.do?operate=toRegist," +
                                                "/book/page.do?operate=toIndex," +
                                                "/book/user.do?null" +
                                                "/kaptcha.jpg" ,
                                        description = "过滤器白名单"))
public class SessionFilter implements Filter {

    private String whiteListStr;

    public void init(FilterConfig config) throws ServletException {
        whiteListStr = config.getInitParameter("whiteListStr");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();

        LoggerUtils.logInfo("RequestURI : " + requestURI);
        LoggerUtils.logInfo("QueryString : " + queryString);


        if (whiteListStr.contains(requestURI + "?" + queryString)) {
            chain.doFilter(request, response);
        } else {

            Object userObj = httpServletRequest.getSession().getAttribute("user");
            if (userObj == null) {
                httpServletResponse.sendRedirect("page.do?operate=toLogin");
            } else {
                request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
                chain.doFilter(request, response);
            }
        }
    }
}
