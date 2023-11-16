package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task5 {

  public static void main(String[] args) {
    String textpath = "src/main/resources/task5/text.txt";
    String countpath = "src/main/resources/task5/count.txt";
    String wordsFolder = "src/main/resources/task5/";
    runTextProcessing(textpath, countpath,wordsFolder);
  }

  public static void runTextProcessing(String textPath, String countPath, String wordsFolder) {
    try (var fileStream = Files.lines(Path.of(textPath));
        var fileWriter = new FileWriter(countPath)) {
      fileStream
          .flatMap(line -> Arrays.stream(line.split("\\s+")))
          .filter(str -> !str.isEmpty())
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet()
          .forEach(entry -> {
            String word = entry.getKey();
            Long num = entry.getValue();
            String line = String.format("%s %d", word, num) + System.lineSeparator();
            try {
              fileWriter.write(line);
            } catch (IOException e) {
              String msg = String.format("An error occurred while writing to file for line: %s",
                  line);
              throw new RuntimeException(msg, e);
            }
            writeWordsToWordFile(word, num, wordsFolder);
          });
    } catch (IOException e) {
      throw new RuntimeException("Something is wrong with text/count files", e);
    }
  }

  public static void writeWordsToWordFile(String word, Long wordNumber,String folder) {
    String path = folder + "%s.txt".formatted(word);
    try (var wordFile =
        new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(path)))) {
      while (wordNumber > 1) {
        wordFile.write(word + " ");
        wordNumber--;
      }
      wordFile.write(word + System.lineSeparator());
    } catch (IOException e) {
      String msg = String.format("An error occurred while writing to file \"%s\"", word + ".txt");
      throw new RuntimeException(msg, e);
    }
  }
}
