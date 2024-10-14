package com.ssctech.vendingmachine.domain.state.money;

import java.util.function.Supplier;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Money;

public class MoneyInventoryHeld {

  private Money moneyInventory = Money.ZERO;

  private static MoneyInventoryHeld soleInstance = new MoneyInventoryHeld();

  private Supplier<Money> clientSpecificMachineRefill;

  private MoneyInventoryHeld() {
  }

  public static MoneyInventoryHeld instance() {
    return soleInstance;
  }

  public MoneyInventoryHeld add(Coin coin, Integer amount) {
    this.moneyInventory = moneyInventory.add(coin, amount);
    return this;
  }

  public MoneyInventoryHeld add(Money money) {
    this.moneyInventory = moneyInventory.add(money);
    return this;
  }

  public void resetInventoryToZero() {
    this.moneyInventory = Money.ZERO;
  }

  public Money withdrawAllMoney() {
    Money currentInventory = moneyInventory;
    resetInventoryToZero();
    return currentInventory;
  }

  public void setClientSpecificMachineRefillOperation(Supplier<Money> clientSpecificMachineRefill) {
    this.clientSpecificMachineRefill = clientSpecificMachineRefill;
  }

  public void operatorRefillWithCoins() {
    if (clientSpecificMachineRefill == null) {
      throw new RuntimeException("Machine is not properly set. Client specific refill operation is not defined.");
    }
    moneyInventory = clientSpecificMachineRefill.get();
  }

  public Money getInventory() {
    return moneyInventory;
  }
}
