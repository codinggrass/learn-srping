package learn.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AppListener
 * @Description ServletContextListener 提供监听context初始化以及销毁事件执行的接口
 * @Author codegrass
 * @Date 2022/3/30 22:31
 * @Version 1.0
 */
@WebListener
@Slf4j
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> countries = new HashMap<>();
        countries.put("cn", "China");
        countries.put("us", "United States");

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("countries", countries);

        log.info("打印属性countries", servletContext.getAttribute("countries"));

        printAllAttributes(servletContext);
    }

    private void printAllAttributes(ServletContext servletContext) {

        Enumeration<String> attributes = servletContext.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attribute = attributes.nextElement();
            log.info("AppListener.printAllAttributes: key {},value {}", attribute, servletContext.getAttribute(attribute));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        log.info("listener AppListener contextDestroyed:{}", sce.getServletContext().getAttributeNames().nextElement());
    }
}
