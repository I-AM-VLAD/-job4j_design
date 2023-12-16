package ru.job4j.inout.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void when2Periods(@TempDir Path tempDir) throws IOException {
        File source = new File("data/when2Periods.log");
        File target = tempDir.resolve("target.csv").toFile();
        Analysis.unavailable(source.getPath(), target.getPath());
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(result::add);
        }
        assertThat("10:57:01;10:59:01" + "11:01:02;11:02:02").hasToString(result.get(0) + result.get(1));
    }

    @Test
    void whenNotEnd(@TempDir Path tempDir) throws IOException {
        File source = new File("data/whenNotEnd.log");
        File target = tempDir.resolve("target.csv").toFile();
        Analysis.unavailable(source.getPath(), target.getPath());
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(result::add);
        }
        assertThat("10:57:01;" + null).hasToString(result.get(0));
    }
}