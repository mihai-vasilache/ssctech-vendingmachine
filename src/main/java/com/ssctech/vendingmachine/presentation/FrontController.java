package com.ssctech.vendingmachine.presentation;

import com.ssctech.vendingmachine.presentation.supertype.ConsoleDisplay;
import com.ssctech.vendingmachine.presentation.supertype.ConsoleInputReader;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult.Operation;
import com.ssctech.vendingmachine.presentation.supertype.Display;
import com.ssctech.vendingmachine.presentation.supertype.InputReader;

public class FrontController {

  private Controller startingPage = new MainMenuController();
  private Display display = new ConsoleDisplay();
  private InputReader inputReader = new ConsoleInputReader();

  public void start() {
    ControllerResult controllerResult = ControllerResult.redirect(new MainMenuController());
    String userInputValue = "";
    while (true) {
      if (controllerResult.getOperation() == Operation.SHUTDOWN_MACHINE) {
        break;
      }
      if (controllerResult.getOperation() == Operation.REDIRECT) {
        userInputValue = "";
        controllerResult = controllerResult.getNextController().run(userInputValue, display);
        continue;
      }
      userInputValue = inputReader.readValue().trim().toLowerCase();
      controllerResult = controllerResult.getNextController().run(userInputValue, display);
    }
  }

  public void setInputReader(InputReader inputReader) {
    this.inputReader = inputReader;
  }

  public void setDisplay(Display display) {
    this.display = display;
  }
}
