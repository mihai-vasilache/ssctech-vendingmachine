package com.ssctech.vendingmachine.domain.state.money;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Money;

public class MoneyInventoryHeld {

  private static MoneyInventoryHeld soleInstance = new MoneyInventoryHeld();

  private Money moneyInventory = Money.ZERO;

  private MoneyInventoryHeld() {
  }

  public static MoneyInventoryHeld instance() {
    return soleInstance;
  }

  public MoneyInventoryHeld add(Coin coin, Integer amount) {
    this.moneyInventory = moneyInventory.add(coin, amount);
    return this;
  }

  public Money withdrawAllMoney() {
    Money currentInventory = moneyInventory;
    moneyInventory = Money.ZERO;
    return currentInventory;
  }

  public Money getInventory() {
    return moneyInventory;
  }
}
