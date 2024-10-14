package com.ssctech.vendingmachine.presentation;

import java.util.Set;

import com.ssctech.vendingmachine.domain.product.Product;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.UserBalance;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult;
import com.ssctech.vendingmachine.presentation.supertype.Display;

public class MainMenuController extends Controller {

  private static final String OPERATOR_COMMAND = "op";

  @Override
  public ControllerResult run(String input, Display display) {
    //first run
    if (input.isBlank()) {
      displayMenu(display);
      return ControllerResult.readUserInput(this);
    }
    if (input.equals(OPERATOR_COMMAND)) {
      return ControllerResult.redirect(new OperatorController());
    }
    return displayInvalidCommand(input, display);
  }

  public void displayMenu(Display display) {
    display.newEmptyScreen();
    display.print("Current products:");
    Set<Product> availableProducts = ProductsInventory.instance().getAvailableProducts();
    int productNumber = 1;
    for (Product availableProduct : availableProducts) {
      display.print("\t" + productNumber + ". " +
          availableProduct.getName() + " " +
          availableProduct.getPrice().toPlainString() + " " +
          MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
      );
      productNumber++;
    }
    Set<Product> unavailableProducts = ProductsInventory.instance().getUnavailableProducts();
    for (Product unavailableProduct : unavailableProducts) {
      display.print("\t" + unavailableProduct.getName() + " " + " IS OUT OF STOCK");
    }
    display.print("Your current balance is: " +
        UserBalance.instance().getBalanceCoins().amount().toPlainString() + " " +
        MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
    );
    display.print("(Enter \"op\" for operator menu)");
    display.print("Enter product number or c to insert coins:");
  }

}
