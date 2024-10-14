package com.ssctech.vendingmachine.utils;

import com.ssctech.example.ExampleCoinMachineSetup;
import com.ssctech.example.ExampleProductsMachineSetup;
import com.ssctech.vendingmachine.domain.state.money.MoneyInventoryHeld;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;

public class VendingMachineTest {

  public void initializeMachine() {
    ExampleCoinMachineSetup.machineCoinsSetup();
    fillMachineWithCoins();
    fillMachineWithProducts();
  }

  public void fillMachineWithCoins() {
    MoneyInventoryHeld.instance()
        .add(ExampleCoinMachineSetup.FIVE_CENTS, 50)
        .add(ExampleCoinMachineSetup.TEN_CENTS, 50)
        .add(ExampleCoinMachineSetup.TWENTY_CENTS, 30)
        .add(ExampleCoinMachineSetup.FIFTY_CENTS, 30)
        .add(ExampleCoinMachineSetup.ONE_EURO, 20)
        .add(ExampleCoinMachineSetup.TWO_EURO, 10);
  }

  public void fillMachineWithProducts() {
    ProductsInventory.instance()
        .add(ExampleProductsMachineSetup.COKE, 30)
        .add(ExampleProductsMachineSetup.PEPSI, 50)
        .add(ExampleProductsMachineSetup.WATER, 50);
  }

}
