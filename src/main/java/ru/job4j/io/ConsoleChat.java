package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        List<String> botAnswersList = collectAnswers(botAnswers);
        List<String> chat = new ArrayList<>();

        while (!line.equals(OUT)) {
            if (line.equals(STOP)) {
                while (!line.equals(CONTINUE)) {
                    chat.add(line + System.lineSeparator());
                    line = sc.nextLine();
                }
            }

            chat.add(line + System.lineSeparator());
            String randomText = botAnswersList.get(new Random().nextInt(botAnswersList.size() - 1));
            System.out.println(randomText);
            chat.add(randomText + System.lineSeparator());
            line = sc.nextLine();

        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            chat.forEach(out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> collectAnswers(String path) {
        List<String> botAnswersList = new ArrayList<>();
        InputStream in;
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            botAnswersList = read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botAnswersList;
    }


    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./botLogs.txt", "./botAnswers.txt");
        cc.run();
    }
}