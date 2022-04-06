package learn.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RequestAttributeListener
 * @Description TODO
 * @Author Code Grass
 * @Date 2022/4/6 21:09
 * @Version 1.0
 */
@WebListener
@Slf4j
public class RequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.info("ServletRequest中属性{}新增,值为{}",srae.getName(),srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log.info("ServletRequest中属性{}删除,值为{}",srae.getName(),srae.getValue());

    }
}
