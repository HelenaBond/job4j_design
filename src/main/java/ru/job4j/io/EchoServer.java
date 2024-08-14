package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    String addressString = string.split(" ")[1];
                    String parameters = addressString.substring(addressString.indexOf('?') + 1);
                    if (parameters.contains("msg=Exit")) {
                        server.close();
                    } else if (parameters.contains("msg=Hello")) {
                        output.write("Hello.".getBytes());
                    } else {
                        output.write(parameters.substring(parameters.indexOf("=") + 1).getBytes());
                    }
                    while (string != null && !string.isEmpty()) {
                        System.out.println(string);
                        string = input.readLine();
                    }
                    output.flush();
                }
            }
        }
    }
}
