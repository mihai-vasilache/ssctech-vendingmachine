package com.ssctech.vendingmachine.presentation.supertype;

public abstract class Controller {

  public abstract ControllerResult run(String input, Display display);

  protected ControllerResult displayInvalidCommand(String input, Display display) {
    display.print("Illegal command: \"" + input + "\". Please enter a valid command.");
    return ControllerResult.readUserInput(this);
  }


}
