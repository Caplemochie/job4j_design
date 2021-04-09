package ru.job4j.io;

import java.io.*;

public class Analyze {
    public void unavailable(String source, String target) {
        String line;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {

            while ((line = read.readLine()) != null) {
                while (line != null && line.contains("200") || line.contains("300")) {
                    line = read.readLine();
                }
                sb.append(line.split(" ")[1]).append(";");

                while (line != null && line.contains("400") || line.contains("500")) {
                    line = read.readLine();
                }
                sb.append(line.split(" ")[1]).append(System.lineSeparator());
            }
            out.write(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}