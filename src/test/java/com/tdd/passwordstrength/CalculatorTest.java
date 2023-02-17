package com.tdd.passwordstrength;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

  @Test
  void plus() throws Exception {
    int result = Calculator.plus(1, 2);
    assertEquals(3, result);
    assertEquals(5, Calculator.plus(4, 1));
  }
}
