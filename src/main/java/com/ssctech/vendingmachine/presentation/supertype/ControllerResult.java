package com.ssctech.vendingmachine.presentation.supertype;

import static com.ssctech.vendingmachine.presentation.supertype.ControllerResult.Operation.READ_INPUT;
import static com.ssctech.vendingmachine.presentation.supertype.ControllerResult.Operation.REDIRECT;
import static com.ssctech.vendingmachine.presentation.supertype.ControllerResult.Operation.SHUTDOWN_MACHINE;

public class ControllerResult {

  public enum Operation {
    REDIRECT,
    READ_INPUT,
    SHUTDOWN_MACHINE
  }

  private Controller nextController;
  private Operation operation;

  public ControllerResult(Controller nextController, Operation operation) {
    this.nextController = nextController;
    this.operation = operation;
  }

  public static ControllerResult readUserInput(Controller nextController) {
    return new ControllerResult(nextController, READ_INPUT);
  }

  public static ControllerResult redirect(Controller nextController) {
    return new ControllerResult(nextController, REDIRECT);
  }

  public static ControllerResult shutdownMachine() {
    return new ControllerResult(null, SHUTDOWN_MACHINE);
  }

  public Controller getNextController() {
    return nextController;
  }

  public Operation getOperation() {
    return operation;
  }
}
