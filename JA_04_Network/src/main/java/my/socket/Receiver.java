package my.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String[] args) throws IOException {
        int port = 1122;
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            System.out.println("Receiver listening...");
            Socket incomeSocket = serverSocket.accept();
            System.out.println("Client connected");
            InputStream stream = incomeSocket.getInputStream();
            BufferedReader reader =
                    new BufferedReader(
                      new InputStreamReader(stream)
                    );
            String data = reader.readLine();
            System.out.println(data);
            stream.close();
        }
    }
}
