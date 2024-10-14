package com.ssctech.vendingmachine.presentation;

import java.util.List;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.UserBalance;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult;
import com.ssctech.vendingmachine.presentation.supertype.Display;

public class InsertCoinsController extends Controller {

  private static final String FINISH_COMMAND = "f";

  @Override
  public ControllerResult run(String input, Display display) {
    if (input.equals(FINISH_COMMAND)) {
      return ControllerResult.redirect(new MainMenuController());
    }
    //first run
    if (input.isBlank()) {
      displayMenu(display);
      return ControllerResult.readUserInput(this);
    }

    String[] intputElements = input.split(",");
    if (intputElements.length != 2) {
      return displayInvalidCommand(input, display);
    }
    try {
      int coinChoice = Integer.parseInt(intputElements[0].trim());
      int amountOfCoins = Integer.parseInt(intputElements[1].trim());
      List<Coin> coinTypes = MachineConfiguration.getSupportedCoinTypes().getCoinTypes();
      if (coinChoice < 1 || coinChoice > coinTypes.size()) {
        return displayInvalidCommand(input, display);
      }
      if (amountOfCoins < 0) {
        return displayInvalidCommand(input, display);
      }
      UserBalance.instance().add(coinTypes.get(coinChoice - 1), amountOfCoins);
    } catch (NumberFormatException e) {
      return displayInvalidCommand(input, display);
    }

    displayMenu(display);
    return ControllerResult.readUserInput(this);
  }

  public void displayMenu(Display display) {
    display.newEmptyScreen();
    display.print("Your current balance is: " +
        UserBalance.instance().getBalanceCoins().amount().toPlainString() + " " +
        MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
    );
    display.print("Coin types:");
    List<Coin> coinTypes = MachineConfiguration.getSupportedCoinTypes().getCoinTypes();
    for (int coinTypeIdx = 0; coinTypeIdx < coinTypes.size(); coinTypeIdx++) {
      Coin aCoinType = coinTypes.get(coinTypeIdx);
      display.print((coinTypeIdx + 1) + ". " +
          aCoinType.coinValue().toPlainString() + " " +
          MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
      );
    }
    display.print("Enter \"f\" to finish.");
    display.print("Insert: coin type, amount");
  }
}
