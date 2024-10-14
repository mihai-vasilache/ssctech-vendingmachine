package com.ssctech.vendingmachine;

import com.ssctech.vendingmachine.presentation.FrontController;
import com.ssctech.vendingmachine.utils.TestsDisplay;
import com.ssctech.vendingmachine.utils.TestsDisplay.TestScreen.ScreenLineTextsBuilder;
import com.ssctech.vendingmachine.utils.TestsInputReader;
import com.ssctech.vendingmachine.utils.VendingMachineTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class OperatorResupplyTest extends VendingMachineTest {

  @Test
  public void operatorRefillMachine() {
    initializeMachine();
    TestsInputReader testsInputReader = new TestsInputReader("op", "1", "2");
    TestsDisplay testsDisplay = new TestsDisplay();

    FrontController frontController = new FrontController();
    frontController.setInputReader(testsInputReader);
    frontController.setDisplay(testsDisplay);
    frontController.start();

    testsDisplay.getScreen(2).containsLines(
        new ScreenLineTextsBuilder()
            .line("Machine balance is", "0.00 EUR")
            .getText()
    );

    testsDisplay.getScreen(3).containsLines(
        new ScreenLineTextsBuilder()
            .line("Machine balance is", "92.50 EUR")
            .getText()
    );

    //Assertions.assertTrue();

  }

}