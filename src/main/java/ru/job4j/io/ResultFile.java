package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        ResultFile resultFile = new ResultFile();
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(resultFile.multiple(3).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String multiple(int size) {
        String s = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s += i + " x " + j + " = " + (i * j) + "\n";
            }
        }
        return s;
    }
}
