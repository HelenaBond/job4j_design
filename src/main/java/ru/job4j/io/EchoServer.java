package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
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
        } catch (IOException e) {
            LOG.debug("Something going wrong.", e);
        }
    }
}
