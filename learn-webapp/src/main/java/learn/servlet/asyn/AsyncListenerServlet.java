package learn.servlet.asyn;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import learn.listener.async.MyAsyncListener;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Code Grass
 * @version 1.0
 * @className AsyncListenerServlet
 * @description TODO
 * @date 2022/5/2 11:22
 */
@Slf4j
@WebServlet(name = "AsyncListenerServlet",
        urlPatterns = "/asyncListener",
        asyncSupported = true
)
public class AsyncListenerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(100);
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(() ->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
            String hi_from_lister = "hi from lister";
            log.info("waiting ... ");
            req.setAttribute("greeting",hi_from_lister);
            asyncContext.dispatch("/test-async-listener.jsp");
        });

    }
}
