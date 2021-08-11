package my.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Sender {
    public static void main(String[] args) throws IOException {
        int port = 1122;
        String ip = "127.0.0.1";

        Socket socket = new Socket(ip, port);
        String data = "Hello, world!";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        );
        writer.write(data + "\n");
        writer.close();
        socket.close();
    }
}
