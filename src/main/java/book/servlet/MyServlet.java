package book.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/don'tUse", loadOnStartup = 1)
public class MyServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("                   _ooOoo_"                     );
        System.out.println("                  o8888888o"                    );
        System.out.println("                  88\" . \"88"                  );
        System.out.println("                  o8888888o"                    );
        System.out.println("                  (| -_- |)"                    );
        System.out.println("                   O\\ = /O"                    );
        System.out.println("               ____/`---'\\____"                );
        System.out.println("             .   ' \\\\| |// `."                );
        System.out.println("              / \\\\||| : |||// \\"             );
        System.out.println("            / _||||| -:- |||||- \\"             );
        System.out.println("              | | \\\\\\ - /// | |"             );
        System.out.println("            | \\_| ''\\---/'' | |"              );
        System.out.println("             \\ .-\\__ `-` ___/-. /"            );
        System.out.println("          ___`. .' /--.--\\ `. . __"            );
        System.out.println("       .\"\" '< `.___\\_<|>_/___.' >'\"\"."     );
        System.out.println("      | | : `- \\`.;`\\ _ /`;.`/ - ` : | |"     );
        System.out.println("        \\ \\ `-. \\_ __\\ /__ _/ .-` / /"      );
        System.out.println("======`-.____`-.___\\_____/___.-`____.-'======" );
        System.out.println("                   `=---='"                     );
        System.out.println(""                                               );
        System.out.println(".............................................." );
        System.out.println("           佛祖保佑            永无BUG"           );
        System.out.println("______________________________________________" );
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
