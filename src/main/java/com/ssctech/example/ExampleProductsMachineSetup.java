package com.ssctech.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ssctech.vendingmachine.domain.product.Product;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;

public class ExampleProductsMachineSetup {

  public static Product COKE = new Product("Coke", 1.5);
  public static Product PEPSI = new Product("Pepsi", 1.45);
  public static Product WATER = new Product("Water", 0.9);

  private static Map<Product, Integer> fullProductsMachineCapacity;

  public static void machineProductsSetup() {
    ProductsInventory.instance()
        .add(COKE, 0)
        .add(PEPSI, 0)
        .add(WATER, 0);

    fullProductsMachineCapacity = new LinkedHashMap<>();
    fullProductsMachineCapacity.put(COKE, 20);
    fullProductsMachineCapacity.put(PEPSI, 20);
    fullProductsMachineCapacity.put(WATER, 20);
  }

  public static Map<Product, Integer> getMachineFullCapacityInProducts() {
    return Collections.unmodifiableMap(fullProductsMachineCapacity);
  }

}
