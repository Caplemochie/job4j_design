package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        ResultFile resultFile = new ResultFile();
        try (FileOutputStream out = new FileOutputStream("result1.txt")) {
            out.write(resultFile.multiple(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String multiple(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(i + " x " + j + " = " + (i * j) + System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
