package learn.listener;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName PerfStatListener
 * @Description TODO
 * @Author Code Grass
 * @Date 2022/4/6 20:54
 * @Version 1.0
 */
@WebListener
@Slf4j
public class PerfStatListener implements ServletRequestListener {

    public static final String REQUEST_BEGIN_TIME = "REQUEST_BEGIN_TIME";

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        long beginTime = System.currentTimeMillis();
        servletRequest.setAttribute(REQUEST_BEGIN_TIME, beginTime);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        long beginTime = (long) servletRequest.getAttribute("REQUEST_BEGIN_TIME");
        long endTime = System.currentTimeMillis();
        String uri = servletRequest.getRequestURI();

        log.info("request {} time cost {} ms",
                uri,
                endTime - beginTime);
    }
}
