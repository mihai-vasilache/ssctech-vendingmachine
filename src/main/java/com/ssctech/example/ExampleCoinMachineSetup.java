package com.ssctech.example;

import java.util.HashMap;
import java.util.Map;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Coin.Currencies;
import com.ssctech.vendingmachine.domain.money.Money;
import com.ssctech.vendingmachine.domain.state.MachineConfiguration;
import com.ssctech.vendingmachine.domain.state.money.AllowedMachineCoinTypes;

public class ExampleCoinMachineSetup {

  public static Coin FIVE_CENTS = new Coin(Currencies.EUR, 0.05);
  public static Coin TEN_CENTS = new Coin(Currencies.EUR, 0.10);
  public static Coin TWENTY_CENTS = new Coin(Currencies.EUR, 0.20);
  public static Coin FIFTY_CENTS = new Coin(Currencies.EUR, 0.50);
  public static Coin ONE_EURO = new Coin(Currencies.EUR, 1);
  public static Coin TWO_EURO = new Coin(Currencies.EUR, 2);

  private static Map<Coin, Integer> fullCoinsMachineCapacity;

  public static void machineCoinsSetup() {
    AllowedMachineCoinTypes allowedMachineCoinTypes = AllowedMachineCoinTypes.builder()
        .addCoinType(FIVE_CENTS)
        .addCoinType(TEN_CENTS)
        .addCoinType(TWENTY_CENTS)
        .addCoinType(FIFTY_CENTS)
        .addCoinType(ONE_EURO)
        .addCoinType(TWO_EURO)
        .build();
    MachineConfiguration.configure(allowedMachineCoinTypes);

    fullCoinsMachineCapacity = new HashMap<>();
    fullCoinsMachineCapacity.put(FIVE_CENTS, 50);
    fullCoinsMachineCapacity.put(TEN_CENTS, 50);
    fullCoinsMachineCapacity.put(TWENTY_CENTS, 50);
    fullCoinsMachineCapacity.put(FIFTY_CENTS, 30);
    fullCoinsMachineCapacity.put(ONE_EURO, 20);
    fullCoinsMachineCapacity.put(TWO_EURO, 20);
  }

  public static Money getMachineFullCapacityInMoney() {
    Money.Builder moneyBuilder = Money.builder();
    fullCoinsMachineCapacity.entrySet().stream().forEach(
        (maxCoinCapacity) -> moneyBuilder.add(maxCoinCapacity.getKey(), maxCoinCapacity.getValue())
    );
    return moneyBuilder.build();
  }
}
