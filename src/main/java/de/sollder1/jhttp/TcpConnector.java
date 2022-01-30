package de.sollder1.jhttp;

import de.sollder1.jhttp.config.ConfigManager;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpConnector {

    private final int port;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public TcpConnector(int port) {
        this.port = port;
    }

    public void startup() throws IOException {
        serverSocket = new ServerSocket(port);
        Logger.info("TcpConnector on port {} opened.", port);
        executorService = Executors.newFixedThreadPool(ConfigManager.get().getExecutorThreads(), r -> {
            var thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });


        while (true) {
            executorService.submit(new ConnectionHandler(serverSocket.accept()));
        }

    }

    public void shutdown() throws IOException {
        serverSocket.close();
        executorService.shutdown();
        Logger.info("TcpConnector shut down");
    }


}
