package learn.servlet.asyn;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Code Grass
 * @version 1.0
 * @className AsynDispatchServlet
 * @description TODO
 * @date 2022/5/2 9:55
 */
@Slf4j
@WebServlet(name = "AsyncDispatchServlet",
        urlPatterns = { "/asyncDispatch" },
        asyncSupported = true)
public class AsynDispatchServlet extends HttpServlet {
    private static final long serialVersionUID = 222L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        req.setAttribute("mainThread", Thread.currentThread().getName());

        asyncContext.setTimeout(3000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                log.info("begin async run");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                req.setAttribute("workerThread", Thread.currentThread().getName());
                log.info("end async run");
                asyncContext.dispatch("/threadNames.jsp");
            }
        });
    }
}
