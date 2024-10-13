package com.ssctech.vendingmachine.utils;

import com.ssctech.example.ExampleCoinTypes;
import com.ssctech.example.ExampleProducts;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;
import com.ssctech.vendingmachine.domain.state.money.MoneyInventoryHeld;

public class VendingMachineTest {

  public void initializeMachine() {
    ExampleCoinTypes.setupMachineWithAcceptedCoinTypes();
    fillMachineWithCoins();
    fillMachineWithProducts();
  }

  public void fillMachineWithCoins() {
    MoneyInventoryHeld.instance()
        .add(ExampleCoinTypes.FIVE_CENTS, 50)
        .add(ExampleCoinTypes.TEN_CENTS, 50)
        .add(ExampleCoinTypes.TWENTY_CENTS, 30)
        .add(ExampleCoinTypes.FIFTY_CENTS, 30)
        .add(ExampleCoinTypes.ONE_EURO, 20)
        .add(ExampleCoinTypes.TWO_EURO, 10);
  }

  public void fillMachineWithProducts() {
    ProductsInventory.instance()
        .add(ExampleProducts.COKE, 30)
        .add(ExampleProducts.PEPSI, 50)
        .add(ExampleProducts.WATER, 50);
  }

}
