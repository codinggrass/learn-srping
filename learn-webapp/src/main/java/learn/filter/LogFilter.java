package learn.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName LogFilter
 * @Description 通过filter实现简单的记录日志功能
 * @Author Code Grass
 * @Date 2022/4/6 21:29
 * @Version 1.0
 */
public class LogFilter implements Filter {
    private static final String LOG_NAME = "logName";
    private static final String PRE_FIX = "preFix";

    private PrintWriter logger;
    private String prefix;

    /**
     * @return void
     * @Author Code Grass
     * @Description // 初始化filter，获取配置参数。启动时初始化一次
     * @Date 2022/4/6
     * @Param [filterConfig]
     **/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(this.getClass().getName() + ".init");
        String appPath = filterConfig.getServletContext().getRealPath("/");
        String logFileName = filterConfig.getInitParameter(LOG_NAME);
        prefix = filterConfig.getInitParameter(PRE_FIX);
        try {
            logger = new PrintWriter(new File(appPath, logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(this.getClass().getName() + ".doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        logger.println(new Date() + " " + prefix + requestURI);
        logger.flush();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getName() + ".destroy");
        if (logger != null) {
            logger.close();
        }
    }
}
