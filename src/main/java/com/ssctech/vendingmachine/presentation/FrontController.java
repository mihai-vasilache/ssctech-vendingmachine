package com.ssctech.vendingmachine.presentation;

import com.ssctech.vendingmachine.presentation.supertype.ConsoleDisplay;
import com.ssctech.vendingmachine.presentation.supertype.ConsoleInputReader;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.Display;
import com.ssctech.vendingmachine.presentation.supertype.InputReader;

public class FrontController {

  private Controller startingPage = new MainMenuController();
  private Display display = new ConsoleDisplay();
  private InputReader inputReader = new ConsoleInputReader();

  public void start() {
    Controller nextController = startingPage.run("", display);
    while(true) {
      String userInputValue = inputReader.readValue();
      nextController = nextController.run(userInputValue, display);
    }
  }

  public void setInputReader(InputReader inputReader) {
    this.inputReader = inputReader;
  }
}
