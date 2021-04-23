package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile().toString())))) {
            for (Path file : sources) {
                ZipEntry zipEntry = new ZipEntry(file.toFile().getAbsolutePath().substring(3));
                zip.putNextEntry(zipEntry);
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile().toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Zip zip = new Zip();
        ArgsName jvm = ArgsName.of(args);

        List<Path> paths = Search.search(Path.of(jvm.get("d")), p -> !p.toFile().getName().endsWith(jvm.get("e")));
        zip.packFiles(paths, Paths.get(jvm.get("o")));

    }
}