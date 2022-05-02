package learn.filter.executer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName NerworkService
 * @Description TODO
 * @Author Code Grass
 * @Date 2022/5/1 10:17
 * @Version 1.0
 */
@Slf4j
public class NetworkService implements Runnable {

    private final ServerSocket serverSocket;
    private final ExecutorService exePool;

    public NetworkService(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        exePool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                log.info("for-begin" + serverSocket.toString());
                exePool.execute(new Handler(serverSocket.accept()));
                log.info("for-end" + serverSocket.toString());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private class Handler implements Runnable {
        private final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            log.info(new Date() + Thread.currentThread().toString());
            log.info(new Date() + this.getClass().toString() + "Handler: run()");
            log.info(new Date() + this.socket.toString());
        }
    }
}
