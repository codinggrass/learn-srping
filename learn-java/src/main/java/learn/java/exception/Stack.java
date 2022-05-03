package learn.java.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Code Grass
 * @version 1.0
 * @className test
 * @description TODO
 * @date 2022/5/2 21:29F
 */
@Slf4j
public class Stack {
    public static void main(String[] args) {
        factorial(10);
    }

    public static long factorial(int n) {
        log.info("factorial({})", n);

        Throwable throwable = new Throwable();
        StackTraceElement[] stackTraceElement = throwable.getStackTrace();
        for (StackTraceElement traceElement : stackTraceElement) {
            log.info(traceElement.toString());
        }

        long result;
        if (n <= 1)
            result = 1;
        else
            result = n * factorial(n - 1);
        log.info("result {}", result);

        return result;
    }

    public void checkFile() throws IOException {
        InputStream inputStream = new FileInputStream("abc");
        inputStream.read();
    }

}
