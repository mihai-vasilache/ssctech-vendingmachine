package com.ssctech.vendingmachine.presentation;

import java.util.Map;
import java.util.Map.Entry;

import com.ssctech.vendingmachine.domain.product.Product;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.MoneyInventoryHeld;
import com.ssctech.vendingmachine.domain.state.money.UserBalance;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult;
import com.ssctech.vendingmachine.presentation.supertype.Display;

public class OperatorController extends Controller {

  @Override
  public ControllerResult run(String input, Display display) {
    //first run
    if (input.isBlank()) {
      displayMenu(display);
      return ControllerResult.readUserInput(this);
    }
    try {
      int menuChoice = Integer.parseInt(input);
      if (menuChoice == 1) {
        if (UserBalance.instance().getBalanceCoins().amount().doubleValue() > 0) {
          display.print("User balance is :" +
              UserBalance.instance().getBalanceCoins().amount().toPlainString() + " " +
              MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode() + ". " +
              "It will be moved to Machine Inventory."
          );
          MoneyInventoryHeld.instance().add(UserBalance.instance().getBalanceCoins());
          UserBalance.instance().resetBalanceToZero();
        }
        display.print("Withdrawing machine inventory: " +
            MoneyInventoryHeld.instance().getInventory().amount().toPlainString() + " " +
            MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode());
        MoneyInventoryHeld.instance().resetInventoryToZero();
        display.print("Fill machine coins and products to full capacity.....");
        MoneyInventoryHeld.instance().operatorRefillWithCoins();
        ProductsInventory.instance().operatorRefillWithProducts();
        //redisplay the menu with the new values:
        return ControllerResult.redirect(this);
      }
      if (menuChoice == 2) {
        return ControllerResult.shutdownMachine();
      }
      if (menuChoice == 3) {
        return ControllerResult.redirect(new MainMenuController());
      }

    } catch (NumberFormatException e) {
    }
    return displayInvalidCommand(input, display);
  }

  public void displayMenu(Display display) {
    display.newEmptyScreen();
    display.print("Current product balance:");
    Map<Product, Integer> availableProducts = ProductsInventory.instance().getInventory();
    for (Entry<Product, Integer> availableProduct : availableProducts.entrySet()) {
      display.print("\t" + availableProduct.getKey().getName() + " " +
          availableProduct.getValue() + " pcs."
      );
    }
    display.print("Machine balance is: " +
        MoneyInventoryHeld.instance().getInventory().amount().toPlainString() + " " +
        MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
    );
    display.print("Operator commands:");
    display.print("1. Usual machine refill.");
    display.print("2. Shut down the machine.");
    display.print("3. Return to main menu.");
    display.print("Enter command number:");
  }

}
