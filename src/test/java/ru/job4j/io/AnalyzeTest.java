package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenHave2TimeNotWorkServer() {
        Analyze analyze = new Analyze();
        analyze.unavailable("./server.txt", "./target.txt");
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./target.txt"))) {
            rsl = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> expected = new ArrayList<>(List.of(
                "10:57:01;10:59:01",
                "11:01:02;11:02:02"
        ));
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenHaveOneTimeNotWorkServer() {
        Analyze analyze = new Analyze();
        analyze.unavailable("./server1.txt", "./target1.txt");
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./target1.txt"))) {
            rsl = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> expected = new ArrayList<>(List.of(
                "14:20:02;22:05:09"
        ));
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenHave4TimeNotWorkServer() throws IOException {
        File source = folder.newFile("server.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("400 12:44:00");
            out.println("300 13:45:00");
            out.println("200 15:42:00");
            out.println("500 16:00:00");
            out.println("200 16:30:00");
            out.println("400 16:41:00");
            out.println("300 16:55:00");

        }
        Analyze analyze = new Analyze();
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(sj::add);
        }
        assertThat(sj.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add("12:44:00;13:45:00")
                        .add("16:00:00;16:30:00")
                        .add("16:41:00;16:55:00")
                        .toString()
                )
        );
    }
}
