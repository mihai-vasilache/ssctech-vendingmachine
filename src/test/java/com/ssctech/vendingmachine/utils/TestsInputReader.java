package com.ssctech.vendingmachine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssctech.vendingmachine.presentation.supertype.InputReader;

public class TestsInputReader implements InputReader {

  private List<String> userInputValues = new ArrayList<>();

  private int nextReadingValue;

  public TestsInputReader(String... userInputValues) {
    this.userInputValues = Arrays.asList(userInputValues);
  }

  @Override
  public String readValue() {
    nextReadingValue++;
    String valueToBeRead = userInputValues.get(nextReadingValue - 1);
    System.out.println("<<< " + valueToBeRead);
    return valueToBeRead;
  }
}
