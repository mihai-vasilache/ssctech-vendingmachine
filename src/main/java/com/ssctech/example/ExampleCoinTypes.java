package com.ssctech.example;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Coin.Currencies;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.AllowedMachineCoinTypes;

public class ExampleCoinTypes {

  public static Coin FIVE_CENTS = new Coin(Currencies.EUR, 0.05);
  public static Coin TEN_CENTS = new Coin(Currencies.EUR, 0.10);
  public static Coin TWENTY_CENTS = new Coin(Currencies.EUR, 0.20);
  public static Coin FIFTY_CENTS = new Coin(Currencies.EUR, 0.50);
  public static Coin ONE_EURO = new Coin(Currencies.EUR, 1);
  public static Coin TWO_EURO = new Coin(Currencies.EUR, 2);

  public static void setupMachineWithAcceptedCoinTypes() {
    AllowedMachineCoinTypes allowedMachineCoinTypes = AllowedMachineCoinTypes.builder()
        .addCoinType(FIVE_CENTS)
        .addCoinType(TEN_CENTS)
        .addCoinType(TWENTY_CENTS)
        .addCoinType(FIFTY_CENTS)
        .addCoinType(ONE_EURO)
        .addCoinType(TWO_EURO)
        .build();
    MachineConfiguration.configure(allowedMachineCoinTypes);
  }
}
