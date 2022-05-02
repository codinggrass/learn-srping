package learn.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

/**
 * @author Code Grass
 * @version 1.0
 * @className ProgrammaticeServlet
 * @description TODO
 * @date 2022/5/2 17:14
 */
@WebServlet(urlPatterns = {"/prog"})
@Slf4j
public class ProgrammaticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.authenticate(resp)) {
            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Welcome");
            // 获取登录用户细节信息
            Principal userPrincipal = req.getUserPrincipal();
            log.info(userPrincipal.getName());
            log.info(userPrincipal.toString());

        }else{
            System.out.println("user not authenticate");
        }
    }
}
