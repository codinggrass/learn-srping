package learn.filter.executer;

import learn.filter.DownloadCounterFilterTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Code Grass
 * @version 1.0
 * @className ExecuterTest
 * @description TODO
 * @date 2022/5/1 14:35
 */
@Slf4j
public class ExecuterTest {
    public static File logFile=new File("D://", "download.log");
    public static int num=0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        int a = 0;
        while (a < 10) {
            log.info("a= " + a );
            executorService.submit(new Task(a));
            a++;
        }
    }



}

@Slf4j
class Task implements Runnable {
    Task(int taskNum){
        this.taskNum = taskNum;
    }
    int taskNum ;
    Properties downloadLog;
    @SneakyThrows
    @Override
    public void run() {
        System.out.println(new Time(System.currentTimeMillis())+Thread.currentThread().toString()
                + "taskNum start:" + taskNum
        );
        log.info(new Time(System.currentTimeMillis())+Thread.currentThread().toString()
                + "taskNum start:" + taskNum);


        System.out.println(new Time(System.currentTimeMillis())+Thread.currentThread().toString()
        + "taskNum end:" + taskNum
            );
        log.info(new Time(System.currentTimeMillis()) + Thread.currentThread().toString()
                + "taskNum end:" + taskNum);
    }
}
