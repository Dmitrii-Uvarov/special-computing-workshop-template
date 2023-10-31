package ru.spbu.apcyb.svp.tasks;


import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 1. Банкомат
 */
public class Task1 {

  /**
   * Вычисляет всевозможные варианты разложения {@code sum} в по номиналам из {@code numbers}
   * с точностью до перестановки слагаемых. Числа в каждом разложении упорядочены по неубыванию.
   * Повторяющиеся номиналы в {@code numbers} удаляются.
   *
   * @return список разложений {@code sum} в сумму по номиналам. Список будет пуст, если {@code sum}
   * невозможно разложить по данным номиналам.
   * @throws IllegalArgumentException - если среди {@code sum} и номиналов из {@code numbers}
   * есть неположительные.
   */
  public static List<List<Integer>> computeCombinations(int sum, List<Integer> numbers) {
    validateInput(sum, numbers);

    numbers = new ArrayList<>(numbers.stream().distinct().toList());
    Collections.sort(numbers);

    List<List<Integer>> combs = new ArrayList<>();
    List<Integer> r = new ArrayList<>();
    computeCombinations(sum, numbers, combs, r, 0);
    return combs;
  }

  /**
   * Вспомогательная функция
   */
  public static void computeCombinations(int amount, List<Integer> numbers,
      List<List<Integer>> combinations, List<Integer> comb, int i) {

    if (amount == 0) {
      combinations.add(new ArrayList<>(comb));
      return;
    }

    while (i < numbers.size() && amount - numbers.get(i) >= 0) {
      comb.add(numbers.get(i));
      computeCombinations(amount - numbers.get(i), numbers, combinations, comb, i);
      i++;
      comb.remove(comb.size() - 1);
    }
  }

  /**
   * Проверяет, являются ли сумма и номиналы положительными.
   */
  public static void validateInput(int sum, List<Integer> numbers) {
    if (sum <= 0 || numbers.stream().anyMatch(n -> (n <= 0))) {
      throw new IllegalArgumentException("The sum and numbers must be positive");
    }
  }

  /**
   * Пример работы computeCombinations.
   */
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Task1.class.getName());

    Scanner sc = new Scanner(System.in);
    int sum = parseInt(sc.nextLine());

    String numbers = sc.nextLine();
    List<Integer> nums = new ArrayList<>(Arrays
        .stream(numbers.split("\\s"))
        .map(Integer::parseInt)
        .toList());

    List<List<Integer>> ans = computeCombinations(sum, nums);

    logger.log(Level.INFO, "number of possible combinations: {0}", ans.size());
    logger.log(Level.INFO, "combinations:");
    ans.forEach(c -> logger.log(Level.INFO, c.toString()));
  }
}
