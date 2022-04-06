package learn.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName ImageProtectFileter
 * @Description  保护影像不被直接下载、访问
 * @Author Code Grass
 * @Date 2022/4/6 22:15
 * @Version 1.0
 */
@Slf4j
public class ImageProtectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String referer = httpServletRequest.getHeader("referer");
        log.info("header referer is {}", referer);
        if (referer != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        throw new ServletException("Image not available");
    }
}

