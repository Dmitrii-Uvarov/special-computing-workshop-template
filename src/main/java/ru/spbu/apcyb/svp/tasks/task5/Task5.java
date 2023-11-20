package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task5 {

  public static void main(String[] args) {
    String textpath = "src/main/resources/task5/text.txt";
    String countpath = "src/main/resources/task5/count.txt";
    String wordsfolder = "src/main/resources/task5/words/";

    runTextProcessing(textpath, countpath, wordsfolder);
  }

  public static void runTextProcessing(String textPath, String countPath, String wordsFolder) {
    try (var fileStream = Files.lines(Path.of(textPath));
        var fileWriter = new FileWriter(countPath)) {
      fileStream
          .flatMap(line -> Arrays.stream(line.split("[^a-zA-ZЁёА-я0-9]")))
          .filter(str -> !str.isEmpty())
          .map(String::toLowerCase)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .forEach((word, num) -> {
            String line = "%s %d".formatted(word, num) + System.lineSeparator();
            try {
              fileWriter.write(line);
            } catch (IOException e) {
              String msg = "An error occurred while writing to file for line \"%s\"".formatted(
                  line);
              throw new RuntimeException(msg, e);
            }
            CompletableFuture.runAsync(() -> writeWordsToWordFile(word, num, wordsFolder));
          });

    } catch (IOException e) {
      throw new RuntimeException("Something is wrong with text/count files", e);
    }
  }

  public static void writeWordsToWordFile(String word, Long wordNumber, String folder) {
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
      System.out.println("printing word " + word + " complete");
    } catch (IOException e) {
      String msg = "An error occurred while writing to file " + word + ".txt";
      throw new RuntimeException(msg, e);
    }
  }
}
