package ru.spbu.apcyb.svp.tasks.task1;


import static org.junit.Assert.assertThrows;

import java.io.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task1Test {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  @BeforeEach
  public void setUpOutput() {
    ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "5 \n",
      "5\n 1 2 3\n",
      "5\n1  2  3\n",
      "5\n1,2,3\n",
      "asd\n2 1\n",
      "3+2\n1+1 2+1\n",
      "5\n\n",
      " \n",
      "\n",
      "5.5\n1 2.2 3\n"
  })
  void testWrongFormatInput(String input) {
    provideInput(input);

    assertThrows(NumberFormatException.class, () -> Task1.main(null));
  }

  @Test
  void testEmptyNumbersInput() {
    final String input = "5\n \n";
    provideInput(input);

    assertThrows(IllegalArgumentException.class, () -> Task1.main(null));
  }
}