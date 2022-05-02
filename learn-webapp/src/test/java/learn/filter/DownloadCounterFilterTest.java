package learn.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

@Slf4j
public class DownloadCounterFilterTest {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Properties downloadLog;
    File logFile=new File("D://", "download.log");
    int num = 0;

    @Before
    public void init() {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Test
    public void test_file() {
        if (logFile.exists()) {
            log.info("test_file" + logFile.getAbsolutePath());
        } else
            log.info("logFile not exist");
    }

    @Test
    public void muti_download() {
        int a = 0;
        while (a < 10) {
            executorService.submit(new Task());
            a++;
        }
        log.info("" + num);
        executorService.shutdown();
    }

    class Task implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            num++;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(new Date() + " begin " +
                    Thread.currentThread().toString());
            stringBuffer.append(new Date() + " begin " +
                    Thread.currentThread().toString()+" num is " + num);
            System.out.println(new Date() + " begin " +
                    Thread.currentThread().toString());
            downloadLog = new Properties();
            try {
                downloadLog.load(new FileReader(logFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            downloadLog.setProperty("num", stringBuffer.toString());

            try {
                downloadLog.store(new FileWriter(logFile), "downloadLogs count the url");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
