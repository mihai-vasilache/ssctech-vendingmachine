package com.ssctech.vendingmachine.utils;

import static org.junit.jupiter.api.AssertionFailureBuilder.assertionFailure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssctech.vendingmachine.presentation.supertype.Display;
import org.junit.jupiter.api.Assertions;

public class TestsDisplay implements Display {
  List<TestScreen> outputScreens = new ArrayList<>();

  @Override
  public void newEmptyScreen() {
    System.out.println("*********** NEW SCREEN ******************");
    outputScreens.add(new TestScreen());
  }

  @Override
  public void print(String input) {
    if(outputScreens.isEmpty()) {
      throw new RuntimeException("No screen is created to add lines to it.");
    }
    outputScreens.get(outputScreens.size() - 1).addLine(input);
  }

  public TestScreen getScreen(int screenNumber) {
    return outputScreens.get(screenNumber - 1);
  }

  public static class TestScreen {
    private List<String> screenLines = new ArrayList<>();

    public void addLine(String newLine) {
      System.out.println(newLine);
      screenLines.add(newLine);
    }

    public List<String> getScreenLines() {
      return screenLines;
    }

    public void containsLines(List<List<String>> textToSearch) {
      int currentLineToSearch = 0;
      for(String aScreenLine : screenLines) {
        int lastIndexOfFoundText = -1;
        for(int srcIdx = 0; srcIdx < textToSearch.get(currentLineToSearch).size(); srcIdx++ ) {
          String aTextToSrc = textToSearch.get(currentLineToSearch).get(srcIdx);
          int currentIndexOfFoundText = aScreenLine.indexOf(aTextToSrc);
          if(lastIndexOfFoundText == -1 && srcIdx == 0) {
            continue;
          }
          if(lastIndexOfFoundText == -1 && srcIdx != 0) {
            assertionFailure()
                .message("Screen line does not match")
                .expected(textToSearch.get(currentLineToSearch))
                .actual(aScreenLine)
                .buildAndThrow();
          }
          if(currentIndexOfFoundText <= lastIndexOfFoundText) {
            assertionFailure()
                .message("Screen line does not match")
                .expected(textToSearch.get(currentLineToSearch))
                .actual(aScreenLine)
                .buildAndThrow();
          }
        }
      }
      if(currentLineToSearch < textToSearch.size() - 1) {
        assertionFailure()
            .message("Not all lines found")
            .expected(textToSearch)
            .actual(screenLines)
            .buildAndThrow();
      }
    }

    public static class ScreenLineTextsBuilder {
      List<List<String>> text = new ArrayList<>();

      public ScreenLineTextsBuilder line(String... newStrings) {
        text.add(Arrays.asList(newStrings));
        return this;
      }

      public List<List<String>> getText() {
        return text;
      }
    }

  }


}
