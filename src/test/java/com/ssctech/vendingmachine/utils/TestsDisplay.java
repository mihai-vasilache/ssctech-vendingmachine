package com.ssctech.vendingmachine.utils;

import java.util.ArrayList;
import java.util.List;

import com.ssctech.vendingmachine.presentation.supertype.Display;

public class TestsDisplay implements Display {
  List<TestScreen> outputScreens = new ArrayList<>();

  @Override
  public void newEmptyScreen() {
    outputScreens.add(new TestScreen());
  }

  @Override
  public void print(String input) {
    if(outputScreens.isEmpty()) {
      throw new RuntimeException("No screen is created to add lines to it.");
    }
    outputScreens.get(outputScreens.size() - 1).addLine(input);
  }

  public static class TestScreen {
    private List<String> screenLines = new ArrayList<>();

    public void addLine(String newLine) {
      screenLines.add(newLine);
    }

    public List<String> getScreenLines() {
      return screenLines;
    }
  }


}
