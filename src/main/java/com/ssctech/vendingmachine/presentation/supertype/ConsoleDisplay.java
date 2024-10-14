package com.ssctech.vendingmachine.presentation.supertype;

public class ConsoleDisplay implements Display {

  @Override
  public void newEmptyScreen() {
    System.out.println("*********** NEW SCREEN ******************");
  }

  @Override
  public void print(String input) {
    System.out.println(input);
  }

}
