package Chapter15;

import java.io.*;
import java.net.Socket;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 15:54
 **/
public class ClientHandler implements Runnable {

    private final Socket socket;

    private boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                Reader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream())
        ) {
            while (running) {
                String message = ((BufferedReader) reader).readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Receive Message from Client--->" + message + "-----IP --->" + socket.getRemoteSocketAddress()+"\n");
                writer.write("echo" + message+"\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.stop();
        }

    }

    void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
