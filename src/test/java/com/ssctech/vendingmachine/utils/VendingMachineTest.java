package com.ssctech.vendingmachine.utils;

import com.ssctech.example.ExampleCoinMachineSetup;
import com.ssctech.example.ExampleProductsMachineSetup;
import com.ssctech.vendingmachine.domain.state.money.MoneyInventoryHeld;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;

public class VendingMachineTest {

  protected void initializeMachine() {
    ExampleCoinMachineSetup.machineCoinsSetup();
    ExampleProductsMachineSetup.machineProductsSetup();
    setRefillOperations();
  }

  protected void fillMachine() {
    MoneyInventoryHeld.instance().operatorRefillWithCoins();
    ProductsInventory.instance().operatorRefillWithProducts();
  }

  protected void setRefillOperations() {
    MoneyInventoryHeld.instance().setClientSpecificMachineRefillOperation(
        () -> ExampleCoinMachineSetup.getMachineFullCapacityInMoney()
    );

    ProductsInventory.instance().setClientSpecificMachineRefillOperation(
        ExampleProductsMachineSetup.getMachineRefillOperation()
    );
  }

}
