package learn.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName DownloadcounterFilter
 * @Description TODO
 * @Author Code Grass
 * @Date 2022/4/27 8:23
 * @Version 1.0
 */
@Slf4j
public class DownloadCounterFilter implements Filter {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Properties downloadLog;
    File logFile;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("learn.filter.DownloadCounterFilter init");
        String appPath = filterConfig.getServletContext().getRealPath("/");

        logFile = new File(appPath, "download.log");
        if (!logFile.exists()) {
            try {
                boolean createSuc = logFile.createNewFile();
                if (!createSuc) {
                    log.error("create not succ");
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        downloadLog = new Properties();
        try (FileReader fileReader = new FileReader(logFile)) {
            downloadLog.load(fileReader);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        final String uri = httpServletRequest.getRequestURI();
        executorService.execute(() -> {
            String property = downloadLog.getProperty(uri);
            if (property == null) {
                downloadLog.setProperty(uri, "1");
            } else {
                int count = 0;
                try {
                    count = Integer.parseInt(property);
                } catch (NumberFormatException e) {
                    log.error(e.getMessage(), e);
                }
                count++;
                downloadLog.setProperty(uri, Integer.toString(count));
            }

            try {
                downloadLog.store(new FileWriter(logFile), "downloadLogs count the url");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
