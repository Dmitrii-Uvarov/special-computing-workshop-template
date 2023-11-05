package ru.spbu.apcyb.svp.tasks.task4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NumberGenerator {

  private NumberGenerator() {

  }

  public static void generateSequence(String filename, int count)
      throws IOException {
    try (var fileWriter = new FileWriter(filename)) {
      var random = new Random(System.currentTimeMillis());
      for (int i = 0; i < count; i++) {
        fileWriter.write(random.nextDouble() + System.lineSeparator());
      }
      fileWriter.flush();
    }
  }
}