package com.ssctech.vendingmachine.domain.state.product;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.ssctech.vendingmachine.domain.product.Product;

public class ProductsInventory {

  private static ProductsInventory soleInstance = new ProductsInventory();

  //the value of the map is the number of products inside the machine inventory.
  Map<Product, Integer> inventory = new LinkedHashMap<>();

  private Consumer<Map<Product, Integer>> clientSpecificMachineRefill;

  private ProductsInventory() {
  }

  public static ProductsInventory instance() {
    return soleInstance;
  }

  public ProductsInventory add(Product product, Integer amount) {
    Optional<Product> existingProductWithSameNameButDifferentPrice = inventory.keySet().stream()
        .filter(aProduct -> aProduct.sameNameButDifferentPrice(product))
        .findFirst();
    if (existingProductWithSameNameButDifferentPrice.isPresent()) {
      throw new IllegalArgumentException("A product with the same name, but a different type is already present: "
          + existingProductWithSameNameButDifferentPrice + ". Choose a different name.");
    }
    this.inventory.compute(
        product, (currentKey, currentValue) -> currentValue == null ? amount : currentValue + amount
    );
    return this;
  }

  public void subtractOneProductItem(Product productToSubtract) {
    if (inventory.get(productToSubtract) < 0) {
      throw new IllegalArgumentException("No more items to substract from " + productToSubtract.getName());
    }
    inventory.put(productToSubtract, inventory.size() - 1);
  }

  public List<Product> getAvailableProducts() {
    return inventory.entrySet().stream()
        .filter(productEntry -> productEntry.getValue() > 0)
        .map(productEntry -> productEntry.getKey())
        .collect(Collectors.toList());
  }

  public List<Product> getUnavailableProducts() {
    return inventory.entrySet().stream()
        .filter(productEntry -> productEntry.getValue() == 0)
        .map(productEntry -> productEntry.getKey())
        .collect(Collectors.toList());
  }

  public Map<Product, Integer> getInventory() {
    return Collections.unmodifiableMap(inventory);
  }

  public void setClientSpecificMachineRefillOperation(Consumer<Map<Product, Integer>> clientSpecificMachineRefill) {
    this.clientSpecificMachineRefill = clientSpecificMachineRefill;
  }

  public void operatorRefillWithProducts() {
    if (clientSpecificMachineRefill == null) {
      throw new RuntimeException("Machine is not properly set. Client specific refill operation is not defined.");
    }
    clientSpecificMachineRefill.accept(inventory);
  }
}
