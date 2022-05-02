package learn.listener.async;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Code Grass
 * @version 1.0
 * @className MyAsyncListener
 * @description TODO
 * @date 2022/5/2 11:12
 */
@Slf4j
public class MyAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        log.info("thead:" + Thread.currentThread().getName() +
                " MyAsyncListener onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        log.info("thead:" + Thread.currentThread().getName() +
                " MyAsyncListener onTimeout");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        log.info("thead:" + Thread.currentThread().getName() +
                " MyAsyncListener onError");
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        log.info("thead:" + Thread.currentThread().getName() +
                " MyAsyncListener onStartAsync");
    }
}
