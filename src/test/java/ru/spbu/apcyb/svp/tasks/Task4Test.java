package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.spbu.apcyb.svp.tasks.task4.NumberGenerator;
import ru.spbu.apcyb.svp.tasks.task4.Task4;

/**
 * Тесты для задания 4.
 */
class Task4Test {
    @Test
    void generateSequenceTest() throws IOException {
      var filename = "./src/test/resources/test1.txt";
      NumberGenerator.generateSequence(filename, 10);
      var scanner = new Scanner(new FileReader(filename)).useLocale(Locale.US);

      int counter = 0;
      while (scanner.hasNextDouble()) {
        counter++;
        scanner.nextDouble();
      }
      Assertions.assertEquals(10, counter);
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,1",
        "100,4,10",
        "1000000,4,1000"})
    void runTest(int numbersCount,
                    int threadCount,
                    int numsPerThread)
        throws IOException, ExecutionException, InterruptedException {

      var numbersFilename = "./src/test/resources/test1.txt";
      var answerFilename1 = "./src/test/resources/test2.txt";
      var answerFilename2 = "./src/test/resources/test3.txt";

      NumberGenerator.generateSequence(numbersFilename, numbersCount);
      Task4.run(numbersFilename, answerFilename1, answerFilename2, numbersCount, threadCount, numsPerThread);
      var scanner1 = new Scanner(new FileReader(answerFilename1)).useLocale(Locale.US);
      var scanner2 = new Scanner(new FileReader(answerFilename2)).useLocale(Locale.US);
      int counter = 0;
      while (scanner1.hasNextDouble()) {
        counter++;
        scanner1.nextDouble();
      }
      Assertions.assertEquals(numbersCount, counter);
      counter = 0;
      while (scanner2.hasNextDouble()) {
        counter++;
        scanner2.nextDouble();
      }
      Assertions.assertEquals(numbersCount, counter);
    }
  }