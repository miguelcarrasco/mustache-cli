package com.tlacaelelsoftware;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MustacheCliTest {
    @Test
    void simpleHTMLTest() throws IOException {
        String jsonSimpleTest = "src/test/data/simple-test.json";
        String mustacheSimpleTest = "src/test/data/simple-test.mustache";
        String htmlSimpleTest = "src/test/data/simple-test.html";
        String resultTest = "src/test/data/simple-test-result.html";
        String args[] = {
                "-t", mustacheSimpleTest,
                "-j", jsonSimpleTest,
                "-o", resultTest
        };
        int exitCode = new CommandLine(new MustacheCli()).execute(args);
        assertEquals(0, exitCode);
        try {
            assertEquals(
                    Files.readString(Paths.get(htmlSimpleTest)).trim(),
                    Files.readString(Paths.get(resultTest)).trim()
            );
        } finally {
            new File(resultTest).delete();
        }
    }

    @Test
    void differentDelimitersTest() throws IOException {
        String jsonSimpleTest = "src/test/data/simple-test.json";
        String differentDelimitiersTest = "src/test/data/different-delimiters-test.mustache";
        String htmlSimpleTest = "src/test/data/simple-test.html";
        String resultTest = "src/test/data/simple-test-result.html";
        String args[] = {
                "-t", differentDelimitiersTest,
                "-j", jsonSimpleTest,
                "-o", resultTest,
                "-d", "<%", "%>"
        };
        int exitCode = new CommandLine(new MustacheCli()).execute(args);
        assertEquals(0, exitCode);
        try {
            assertEquals(
                    Files.readString(Paths.get(htmlSimpleTest)).trim(),
                    Files.readString(Paths.get(resultTest)).trim()
            );
        } finally {
            new File(resultTest).delete();
        }
    }
}
