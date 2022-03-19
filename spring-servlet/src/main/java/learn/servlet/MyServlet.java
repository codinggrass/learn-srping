package learn.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @ClassName MyServlet
 * @Description 基础servlet
 * @Author codegrass
 * @Date 2022/3/19 11:04
 * @Version 1.0
 */
@WebServlet(name = "MyServlet", urlPatterns = "/my")
public class MyServlet implements Servlet, Serializable {

    private transient ServletConfig servletConfig;

    public void init(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        String servletName = servletConfig.getServletName();
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.print("<html><head></head>" +
                "<body>Hello from" +
                servletName +
                "</body></html>");
    }

    public String getServletInfo() {
        return "code grass servlet";
    }

    public void destroy() {
        // Do nothing
    }


}
