package com.ssctech.example;


import com.ssctech.vendingmachine.presentation.FrontController;

public class VendingMachineExample {

  public static void main(String[] args) {
    ExampleCoinTypes.setupMachineWithAcceptedCoinTypes();
    ExampleProducts.setupMachineWithAcceptedProducts();

    FrontController frontController = new FrontController();
    frontController.start();

  }
}