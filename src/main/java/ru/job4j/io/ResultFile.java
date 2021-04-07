package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result1.txt")) {
            for (int i = 0; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    out.write(String.format("%d x %d = %d;", i, j, i * j).getBytes());
                    out.write(System.lineSeparator().getBytes());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
