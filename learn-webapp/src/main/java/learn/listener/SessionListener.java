package learn.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SessionListener
 * @Description TODO
 * @Author Code Grass
 * @Date 2022/3/31 21:49
 * @Version 1.0
 */
@WebListener
@Slf4j
public class SessionListener implements HttpSessionListener, ServletContextListener {

    public static final String SESSION_COUNT = "sessionCount";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("sessionCount", new AtomicInteger());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger sessionCount = (AtomicInteger) servletContext.getAttribute(SESSION_COUNT);
        int count = sessionCount.incrementAndGet();
        log.info("a session created, session count is {}",count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        AtomicInteger atomicInteger = (AtomicInteger) servletContext.getAttribute(SESSION_COUNT);
        int count = atomicInteger.decrementAndGet();
        log.info("a session destroyed, session count is {}",count);
    }
}
