package ru.spbu.apcyb.svp.tasks.task1;


import static java.lang.Integer.parseInt;
import static ru.spbu.apcyb.svp.tasks.task1.Atm.computeCombinations;

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

    List<Integer> nums;
    int sum = parseInt(sc.nextLine());

    String numbers = sc.nextLine();
    nums = new ArrayList<>(Arrays
        .stream(numbers.split("\\s"))
        .map(Integer::parseInt)
        .toList());
    sc.close();

    List<List<Integer>> ans = computeCombinations(sum, nums);
    System.out.println(ans.size());
    ans.forEach(System.out::println);
  }
}
