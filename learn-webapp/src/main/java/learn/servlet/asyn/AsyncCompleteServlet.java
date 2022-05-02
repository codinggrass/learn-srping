package learn.servlet.asyn;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Code Grass
 * @version 1.0
 * @className AsyncCompleteServlet
 * @description TODO
 * @date 2022/5/2 10:55
 */
@Slf4j
public class AsyncCompleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html><head><title>" +
                "Async Servlet</title></head>");
        printWriter.println("<body><div id='progress'></div>");
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(60000);
        asyncContext.start(() -> {
            log.info("new thread:" + Thread.currentThread());
            for (int i = 0; i < 100; i++) {
                printWriter.println("<script>");
                printWriter.println("document.getElementById('progress')" +
                        ".innerHTML ='" +
                        (i) + "% complete'");
                printWriter.println("</script>");
                printWriter.flush();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
            }
            printWriter.println("<script>");
            printWriter.println("document.getElementById(" +
                    "'progress').innerHTML = 'DONE'");
            printWriter.println("</script>");
            asyncContext.complete();
        });
    }
}
