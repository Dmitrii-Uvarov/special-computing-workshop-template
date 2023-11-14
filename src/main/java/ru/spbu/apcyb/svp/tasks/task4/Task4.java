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
    int numsPerThreadCount = 1000;

    run(numbersFile, singleAnswerFile, multiAnswerFile, numsCount, threadCount, numsPerThreadCount);
  }

  public static void run(String numbersFile, String singleAnswerFile, String multiAnswerFile,
      int numsCount, int threadCount, int numsPerThreadCount)
      throws IOException, ExecutionException, InterruptedException {

    String msg = """
        multi-thread vs single-thread tangent comparison
        amount of numbers in file: %d
        amount of threads: %d
        amount of numbers per thread: %d
        ----------------------------------------
        """.formatted(numsCount, threadCount, numsPerThreadCount);
    logger.log(Level.INFO, msg);

    NumberGenerator.generateSequence(numbersFile, numsCount);

    var singleStart = System.currentTimeMillis();
    singleThreadTan(numbersFile, singleAnswerFile);
    var singleEnd = System.currentTimeMillis();
    logger.log(Level.INFO, "single-thread time: {0} ms", singleEnd - singleStart);

    var multiStart = System.currentTimeMillis();
    multiThreadTan(numbersFile, multiAnswerFile, threadCount, numsPerThreadCount);
    var multiEnd = System.currentTimeMillis();
    logger.log(Level.INFO, "multi-thread time: {0} ms", multiEnd - multiStart);
  }

  public static void singleThreadTan(String numbersFile, String singleAnswerFile)
      throws IOException {
    var sc = new Scanner(new FileReader(numbersFile)).useLocale(Locale.US);
    var fileWriter = new FileWriter(singleAnswerFile);
    while (sc.hasNextDouble()) {
      fileWriter.write(Math.tan(sc.nextDouble()) + System.lineSeparator());
    }
    fileWriter.flush();
    sc.close();
  }


  private static void multiThreadTan(String numbersFile, String multiAnswerFile, int threadCount,
      int numsPerThread)
      throws IOException, ExecutionException, InterruptedException {

    var exService = Executors.newFixedThreadPool(threadCount);
    Scanner sc = new Scanner(new FileReader(numbersFile)).useLocale(Locale.US);

    List<Future<String>> futures = new ArrayList<>();
    List<Double> buff = new ArrayList<>();
    while (sc.hasNextDouble()) {
      buff.add(sc.nextDouble());
      if (buff.size() >= numsPerThread) {
        List<Double> h = new ArrayList<>(buff);
        futures.add(exService.submit(() -> countTanForOneLine(h)));
        buff.clear();
      }
    }
    if (!buff.isEmpty()) {
      futures.add(exService.submit(() -> countTanForOneLine(new ArrayList<>(buff))));
    }
    sc.close();

    try (var fileWriter = new FileWriter(multiAnswerFile)) {
      for (var future : futures) {
        fileWriter.write(future.get());
      }
      fileWriter.flush();
    }
    exService.shutdown();
  }

  private static String countTanForOneLine(List<Double> line) {
    var answer = new StringBuilder();
    line.forEach(d -> answer.append(Math.tan(d)).append(System.lineSeparator()));
    return answer.toString();
  }
}

