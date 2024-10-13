package com.ssctech.example;

import com.ssctech.vendingmachine.domain.product.Product;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;

public class ExampleProducts {

  public static Product COKE = new Product("Coke", 1.5);
  public static Product PEPSI = new Product("Pepsi", 1.45);
  public static Product WATER = new Product("Water", 0.9);

  public static void setupMachineWithAcceptedProducts() {
    ProductsInventory.instance()
        .add(ExampleProducts.COKE, 0)
        .add(ExampleProducts.PEPSI, 0)
        .add(ExampleProducts.WATER, 0);
  }

}
