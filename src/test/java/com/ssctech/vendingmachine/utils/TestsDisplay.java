package com.ssctech.vendingmachine.utils;

import static org.junit.jupiter.api.AssertionFailureBuilder.assertionFailure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssctech.vendingmachine.presentation.supertype.Display;

public class TestsDisplay implements Display {

  List<TestScreen> outputScreens = new ArrayList<>();

  @Override
  public void newEmptyScreen() {
    System.out.println("*********** NEW SCREEN ******************");
    outputScreens.add(new TestScreen());
  }

  @Override
  public void print(String input) {
    if (outputScreens.isEmpty()) {
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
      for (String aScreenLine : screenLines) {
        int lastIndexOfFoundText = -1;
        for (int searchIdx = 0; searchIdx < textToSearch.get(currentLineToSearch).size(); searchIdx++) {
          String aTextToSearch = textToSearch.get(currentLineToSearch).get(searchIdx);
          int currentIndexOfFoundText = aScreenLine.indexOf(aTextToSearch);
          //skip screen line. Is not in this line
          if (currentIndexOfFoundText == -1 && searchIdx == 0) {
            break;
          }
          //some text parts are found, but the rest of the line parts does not match.
          if (currentIndexOfFoundText == -1 && searchIdx != 0) {
            assertionFailure()
                .message("Screen line does not match")
                .expected(textToSearch.get(currentLineToSearch))
                .actual(aScreenLine)
                .buildAndThrow();
          }
          //found, but is not in the right position.
          if (currentIndexOfFoundText <= lastIndexOfFoundText) {
            assertionFailure()
                .message("Screen line does not match")
                .expected(textToSearch.get(currentLineToSearch))
                .actual(aScreenLine)
                .buildAndThrow();
          }
          //if text part found and is the last in the line to search, advance to next line
          if (currentIndexOfFoundText >= 0 && searchIdx == (textToSearch.get(currentLineToSearch).size() - 1)) {
            currentLineToSearch++;
            break;
          }
          lastIndexOfFoundText = currentIndexOfFoundText;
        }
        //no need to continue, all lines are found
        if (currentLineToSearch == textToSearch.size()) {
          return;
        }
      }
      //not all lines found...
      if (currentLineToSearch < textToSearch.size()) {
        assertionFailure()
            .message("One of the lines is not found")
            .expected(textToSearch.get(currentLineToSearch))
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
