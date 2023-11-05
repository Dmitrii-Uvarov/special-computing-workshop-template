package ru.spbu.apcyb.svp.tasks.task1;


import static java.lang.Integer.parseInt;
import static ru.spbu.apcyb.svp.tasks.task1.Atm.getCombinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Задание 1. Банкомат
 */
public class Task1 {

  /**
   * Точка входа.
   */
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter amount: ");
    int sum = parseInteger(sc.nextLine());

    System.out.print("Enter denominations: ");
    String numbers = sc.nextLine();
    List<Integer> nums = new ArrayList<>(Arrays
        .stream(numbers.split("\\s"))
        .filter(s -> !s.isEmpty())
        .map(Task1::parseInteger)
        .toList());
    sc.close();

    List<List<Integer>> ans = getCombinations(sum, nums);
    System.out.println(ans.size());
    ans.forEach(System.out::println);
  }

  public static int parseInteger(String s) {
    try {
      return parseInt(s);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("unable to convert string \"" + s + "\" to integer");
    }
  }
}
