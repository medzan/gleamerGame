package com.ezangui.gleamer;

import com.ezangui.gleamer.legacy.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class BuildLegacyOutput {

    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeAll
    public static void removeFolderContents() throws FileNotFoundException {
      //  emptyFolder(legacy);
        FileOutputStream fileOutputStream = new FileOutputStream("src/test/resources/current/100-actual.txt");
        // Create a new PrintStream that writes to the file
        PrintStream filePrintStream = new PrintStream(fileOutputStream);
        System.setOut(new PrintStream(filePrintStream));
    }
    public static void emptyFolder(Path path) {
        try { Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void create_test_cases() throws Exception {
        GameRunner.run(new Game(12), 100L);
        Path actual = Paths.get("src/test/resources/current/100-actual.txt");
        Path expected = Paths.get("src/test/resources/legacy/100-expected.txt");
        String actualContent = Files.readString(actual);
        String expectedContent = Files.readString(expected);
        Assertions.assertEquals(expectedContent, actualContent);
    }



}