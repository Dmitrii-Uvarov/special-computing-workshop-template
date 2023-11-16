package ru.spbu.apcyb.svp.tasks.task4;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task4 {

  static final Logger logger = Logger.getLogger("");

  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    String numbersFile = "./src/main/resources/numbersGen.txt";
    String singleAnswerFile = "./src/main/resources/tanAnsSingle.txt";
    String multiAnswerFile = "./src/main/resources/tanAnsMulti.txt";
    int numsCount = 100000;
    int threadCount = 4;

    run(numbersFile, singleAnswerFile, multiAnswerFile, numsCount, threadCount);
  }

  public static void run(String numbersFile, String singleAnswerFile, String multiAnswerFile,
      int numsCount, int threadCount)
      throws IOException, ExecutionException, InterruptedException {

    String msg = """
        multi-thread vs single-thread tangent comparison
        amount of numbers in file: %d
        amount of threads: %d
        ----------------------------------------
        """.formatted(numsCount, threadCount);
    logger.log(Level.INFO, msg);

    NumberGenerator.generateSequence(numbersFile, numsCount);

    var singleStart = System.currentTimeMillis();
    singleThreadTan(numbersFile, singleAnswerFile);
    var singleEnd = System.currentTimeMillis();
    logger.log(Level.INFO, "single-thread time: {0} ms", singleEnd - singleStart);

    var multiStart = System.currentTimeMillis();
    multiThreadTan(numbersFile, multiAnswerFile, threadCount);
    var multiEnd = System.currentTimeMillis();
    logger.log(Level.INFO, "multi-thread time: {0} ms", multiEnd - multiStart);
  }

  public static void singleThreadTan(String numbersFile, String singleAnswerFile)
      throws IOException {
    try (var sc = new Scanner(new FileReader(numbersFile)).useLocale(Locale.US);
        var fileWriter = new FileWriter(singleAnswerFile);) {
      while (sc.hasNextDouble()) {
        fileWriter.write(Math.tan(sc.nextDouble()) + System.lineSeparator());
      }
    }
  }


  private static void multiThreadTan(String numbersFile, String multiAnswerFile, int threadCount)
      throws IOException, ExecutionException, InterruptedException {

    List<Future<Double>> futures = new ArrayList<>();
    ExecutorService exService = null;
    try {
      exService = Executors.newFixedThreadPool(threadCount);
      try (Scanner sc = new Scanner(new FileReader(numbersFile)).useLocale(Locale.US);
          FileWriter fileWriter = new FileWriter(multiAnswerFile)) {

        while (sc.hasNextDouble()) {
          double x = sc.nextDouble();
          futures.add(exService.submit(() -> Math.tan(x)));
        }
        for (var future : futures) {
          fileWriter.write(future.get() + System.lineSeparator());
        }
      }
    } finally {
      if (exService != null) {
        exService.shutdown();
      }
    }
  }
}

