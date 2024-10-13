package com.ssctech.vendingmachine.presentation.supertype;

public class ConsoleDisplay implements Display {

  @Override
  public void print(String input) {
    System.out.println(input);
  }

}
