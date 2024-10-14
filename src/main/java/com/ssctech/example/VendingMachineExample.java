package com.ssctech.example;


import com.ssctech.vendingmachine.domain.state.money.MoneyInventoryHeld;
import com.ssctech.vendingmachine.domain.state.product.ProductsInventory;
import com.ssctech.vendingmachine.presentation.FrontController;

public class VendingMachineExample {

  public static void main(String[] args) {
    ExampleCoinMachineSetup.machineCoinsSetup();
    ExampleProductsMachineSetup.machineProductsSetup();

    MoneyInventoryHeld.instance().setClientSpecificMachineRefillOperation(
        () -> ExampleCoinMachineSetup.getMachineFullCapacityInMoney()
    );

    ProductsInventory.instance().setClientSpecificMachineRefillOperation(
        ExampleProductsMachineSetup.getMachineRefillOperation()
    );

    FrontController frontController = new FrontController();
    frontController.start();

  }
}