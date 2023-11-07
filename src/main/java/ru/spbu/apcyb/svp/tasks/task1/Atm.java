package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class Atm {

  private Atm() {
  }

  /**
   * Вычисляет всевозможные варианты разложения {@code sum} в по номиналам из {@code numbers} с
   * точностью до перестановки слагаемых. Числа в каждом разложении упорядочены по неубыванию.
   * Повторяющиеся номиналы в {@code numbers} удаляются.
   *
   * @return список разложений {@code sum} в сумму по номиналам. Список будет пуст, если sum
   *     невозможно разложить по данным номиналам.
   * @throws IllegalArgumentException - если среди {@code sum} и номиналов из {@code numbers} есть
   *     неположительные.
   */
  public static List<List<Integer>> getCombinations(int sum, List<Integer> numbers) {
    validateInput(sum, numbers);

    numbers = new ArrayList<>(numbers.stream().distinct().toList());
    Collections.sort(numbers);

    List<List<Integer>> combs = new ArrayList<>();
    List<Integer> r = new ArrayList<>();
    computeCombinationsRecursive(sum, numbers, combs, r, 0);
    return combs;
  }

  /**
   * Рекурсивный алгоритм
   */
  public static void computeCombinationsRecursive(int amount, List<Integer> numbers,
      List<List<Integer>> combinations, List<Integer> comb, int i) {

    if (amount == 0) {
      combinations.add(new ArrayList<>(comb));
      return;
    }

    while (i < numbers.size() && amount - numbers.get(i) >= 0) {
      comb.add(numbers.get(i));
      computeCombinationsRecursive(amount - numbers.get(i), numbers, combinations, comb, i);
      i++;
      comb.remove(comb.size() - 1);
    }
  }


  public static void validateInput(int sum, List<Integer> numbers) {
    if (numbers == null) {
      throw new InputMismatchException("numbers must not be null");
    }
    if (numbers.isEmpty()) {
      throw new IllegalArgumentException("numbers must not be empty");
    }
    if (sum <= 0 || numbers.stream().anyMatch(n -> (n <= 0))) {
      throw new IllegalArgumentException("sum and numbers must be positive");
    }
  }

}
