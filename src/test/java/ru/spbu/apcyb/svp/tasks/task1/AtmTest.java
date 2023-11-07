package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.spbu.apcyb.svp.tasks.task1.Atm.getCombinations;

class AtmTest {

  @Test
  void testComputeCombinationsSingleDenomination() {
    int sum = 5;
    List<Integer> numbers = new ArrayList<>();
    numbers.add(5);
    List<List<Integer>> target = new ArrayList<>();
    target.add(new ArrayList<>(numbers));

    List<List<Integer>> result = getCombinations(sum, numbers);
    assertEquals(target, result);
  }

  @Test
  void testComputeCombinationsMultipleDenominations() {
    int sum = 5;
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    List<List<Integer>> target = new ArrayList<>();
    target.add(Arrays.asList(1, 1, 1, 1, 1));
    target.add(Arrays.asList(1, 1, 1, 2));
    target.add(Arrays.asList(1, 1, 3));
    target.add(Arrays.asList(1, 2, 2));
    target.add(Arrays.asList(2, 3));

    List<List<Integer>> result = getCombinations(sum, numbers);
    assertEquals(target, result);
  }

  @Test
  void testComputeCombinationsNoCombinationsPossible() {
    int sum = 5;
    List<Integer> numbers = new ArrayList<>();
    numbers.add(6);
    List<List<Integer>> target = new ArrayList<>();

    List<List<Integer>> result = getCombinations(sum, numbers);
    assertEquals(target, result);
  }

  @Test
  void testComputeCombinationsZeroAmount() {
    int sum = 0;
    List<Integer> numbers = Arrays.asList(6, 2);

    assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
  }

  @Test
  void testComputeCombinationsNoDenominations() {
    int sum = 5;
    List<Integer> numbers = new ArrayList<>();

    assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
  }

  @Test
  void testComputeCombinationsNegativeAmount() {
    int sum = -5;
    List<Integer> numbers = Arrays.asList(6, 2);

    assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
  }

  @Test
  void testGetCombinationsNegativeDenominations() {
    int sum = 5;
    List<Integer> numbers = Arrays.asList(0, -2);

    assertThrows(IllegalArgumentException.class, () -> getCombinations(sum, numbers));
  }

  @Test
  void testComputeCombinationsNullDenominations() {
    int sum = 5;
    List<Integer> numbers = null;

    assertThrows(InputMismatchException.class, () -> getCombinations(sum, numbers));
  }

}
