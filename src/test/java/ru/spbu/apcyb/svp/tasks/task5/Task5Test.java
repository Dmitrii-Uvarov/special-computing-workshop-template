package ru.spbu.apcyb.svp.tasks.task5;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


class Task5Test {


  @Test
  void writeWordsToWordFile() {

    Task5.writeWordsToWordFile("test1", 1L, "./src/test/resources/task5/");

    String path = "./src/test/resources/task5/test1.txt";
    Path filePath = Path.of(path);
    try (BufferedReader fromTest2 =
        new BufferedReader(
            new FileReader(filePath.toFile()))) {
      String currentLine = fromTest2.readLine();
      assertEquals("test1", currentLine);
    } catch (IOException e) {
      throw new RuntimeException("Ошибка при чтении файла", e);
    }
  }

  @Test
  void runTextProcessing() throws IOException {
    int count = 0;
    String textPath = "./src/test/resources/task5/TestTask5.txt";
    String countPath = "./src/test/resources/task5/countsTestFile.txt";
    String wordsFolder = "./src/test/resources/task5/";
    Task5.runTextProcessing(textPath, countPath, wordsFolder);
    String[] words = {"word1", "word2", "word3", "word4", "word5"};
    for (String word : words) {
      Path path = Path.of(wordsFolder + word + ".txt");
      if (Files.exists(path)) {
        count++;
        Files.delete(path);
      }
    }

    int numberOfRightLines = 0;
    Path filePath = Path.of(countPath);
    List<String> lines = Arrays.asList(
        "word1 1", "word2 1", "word3 1", "word4 1", "word5 1");
    try (BufferedReader fromCountsTestFile = new BufferedReader(
        new FileReader(filePath.toFile()))) {
      String currentLine;
      for (int i = 0; i < 5; i++) {
        currentLine = fromCountsTestFile.readLine();
        if (lines.contains(currentLine)) {
          numberOfRightLines++;
        }
      }
    } catch (IOException ex) {
      throw new RuntimeException("Проблема при чтении файла");
    }
    Files.delete(filePath);

    assertEquals(5, count);
    assertEquals(5, numberOfRightLines);
  }

}