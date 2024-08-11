package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                try (BufferedInputStream output = new BufferedInputStream(
                        new FileInputStream(String.valueOf(source)))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zipAll(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validArgs(argsName);
        List<Path> sources = Search.search(Paths.get(argsName.get("d")),
                path -> !path.toFile().getName().endsWith(argsName.get("e")));
        packFiles(sources, Files.createFile(Paths.get(argsName.get("o"))).toFile());
    }

    private void validArgs(ArgsName args) {
        String directory = args.get("d");
        String exclude = args.get("e");
        String output = args.get("o");
        if (directory == null) {
            throw new IllegalArgumentException("Directory argument is required.");
        }
        if (exclude == null) {
            throw new IllegalArgumentException("Exclude argument is required.");
        }
        if (output == null) {
            throw new IllegalArgumentException("Output argument is required.");
        }
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" does not exist.", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("The path \"%s\" exists but is not a directory.", path));
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Not valid exclude format. Exclude must start with \".\"");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Not valid extension format. The archive must have a '.zip' extension");
        }
        if (Files.exists(Paths.get(output))) {
            throw new IllegalArgumentException(String.format("The archive '%s' is already exists", output));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.zipAll(args);
    }
}
