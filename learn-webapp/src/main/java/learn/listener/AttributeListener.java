package learn.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AttributeListener
 * @Description ServletContextAttributeListener 提供context中属性值增、删、改时的事件接口
 * @Author codegrass
 * @Date 2022/3/30 22:52
 * @Version 1.0
 */
@WebListener
@Slf4j
public class AttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        log.info("属性添加： {} {} ", event.getName(), event.getValue());
    }
}
