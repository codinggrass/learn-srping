package learn.servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @ClassName MyServlet
 * @Description 基础servlet
 * @Author Code Grass
 * @Date 2022/3/19 11:04
 * @Version 1.0
 */
//@WebServlet(name = "MyServlet1",
//        urlPatterns = {"/MyServlet1"},
//        initParams = {
//                @WebInitParam(name = "a", value = "b")
//        }
//)
public class MyServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

////    @Override
////    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//////        res.setContentType("text/html");
//////        PrintWriter writer = res.getWriter();
//////        writer.print("<html><head></head>" +
//////                "<body>Hello from" +
//////                req.getServerName() +
//////                "</body></html>");
////        super.service(req,res);
////    }res
//
//    @Override
//    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        super.service(servletRequest, servletResponse);
//    }
}
