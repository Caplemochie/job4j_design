package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {


        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String answer = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("=")) {
                            if (str.contains("Hello")) {
                                answer = "Hello";
                            } else if (str.contains("Exit")) {
                                server.close();
                            } else {
                                answer = str.split("=")[1].split(" ")[0];
                            }
                        }
                        System.out.println(str);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }
}