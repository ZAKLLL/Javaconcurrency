package Chapter15;

import javafx.print.Printer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 15:43
 **/
public class AppServer extends Thread {

    private final int port;
    private final static int DEFAULE_PORT = 10010;
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private volatile boolean start = true;
    private ServerSocket socket;
    private final static ExecutorService excutor = Executors.newFixedThreadPool(10);

    public AppServer() {
        this(DEFAULE_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new ServerSocket(port);
            while (start) {
                Socket accept = socket.accept();
                ClientHandler handler = new ClientHandler(accept);
                excutor.submit(handler);
                clientHandlers.add(handler);
            }
        } catch (IOException e) {
            this.start = false;
        } finally {
            this.dispose();
        }
    }

    private void dispose() {
        clientHandlers.forEach(ClientHandler::stop);
        this.excutor.shutdown();
    }

    void shutDown() throws IOException {
        this.start = false;
        this.interrupt();
        this.socket.close();
    }
}
