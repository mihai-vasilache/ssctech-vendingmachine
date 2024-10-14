package com.ssctech.vendingmachine.presentation;

import java.util.List;

import com.ssctech.vendingmachine.domain.product.Product;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.UserBalance;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;
import com.ssctech.vendingmachine.presentation.supertype.Controller;
import com.ssctech.vendingmachine.presentation.supertype.ControllerResult;
import com.ssctech.vendingmachine.presentation.supertype.Display;

public class MainMenuController extends Controller {

  private static final String OPERATOR_COMMAND = "op";
  private static final String INSERT_COINS_COMMAND = "c";

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
    if (input.equals(INSERT_COINS_COMMAND)) {
      return ControllerResult.redirect(new InsertCoinsController());
    }
    try {
      int productChoice = Integer.parseInt(input);
      List<Product> availableProducts = ProductsInventory.instance().getAvailableProducts();
      if (productChoice < 1 || productChoice > availableProducts.size()) {
        return displayInvalidCommand(input, display);
      }
      Product chosenProduct = availableProducts.get(productChoice - 1);

      if (UserBalance.instance().getBalance().doubleValue() < chosenProduct.getPrice().doubleValue()) {
        display.print("[[[ Not enough money in your balance for " + chosenProduct.getName() + " ]]]");
        displayMenu(display);
        return ControllerResult.readUserInput(this);
      }

      ProductsInventory.instance().subtractOneProductItem(chosenProduct);
      UserBalance.instance().withdraw(chosenProduct.getPrice().doubleValue());

      display.print("[[[ Pick up the chosen product: " + chosenProduct.getName() + " ]]]");
      displayMenu(display);
      return ControllerResult.readUserInput(this);
    } catch (NumberFormatException e) {
    }

    return displayInvalidCommand(input, display);
  }

  public void displayMenu(Display display) {
    display.newEmptyScreen();
    display.print("Current products:");
    List<Product> availableProducts = ProductsInventory.instance().getAvailableProducts();
    int productNumber = 1;
    for (Product availableProduct : availableProducts) {
      display.print("\t" + productNumber + ". " +
          availableProduct.getName() + " " +
          availableProduct.getPrice().toPlainString() + " " +
          MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
      );
      productNumber++;
    }
    List<Product> unavailableProducts = ProductsInventory.instance().getUnavailableProducts();
    for (Product unavailableProduct : unavailableProducts) {
      display.print("\t" + unavailableProduct.getName() + " " + " IS OUT OF STOCK");
    }
    display.print("Your current balance is: " +
        UserBalance.instance().getBalance().toPlainString() + " " +
        MachineConfiguration.getSupportedCoinTypes().getCurrency().getCurrencyCode()
    );
    display.print("(Enter \"op\" for operator menu)");
    display.print("Enter product number or c to insert coins:");
  }

}
