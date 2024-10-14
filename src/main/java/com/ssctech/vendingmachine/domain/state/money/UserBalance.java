package com.ssctech.vendingmachine.domain.state.money;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Money;

public class UserBalance {

  private static UserBalance soleInstance = new UserBalance();

  private Money balance = Money.ZERO;

  private UserBalance() {
  }

  public static UserBalance instance() {
    return soleInstance;
  }

  public UserBalance add(Coin coin, Integer amount) {
    this.balance = balance.add(coin, amount);
    return this;
  }

  public void resetBalanceToZero() {
    this.balance = Money.ZERO;
  }

  public Money withdrawAllMoney() {
    Money currentInventory = balance;
    balance = Money.ZERO;
    return currentInventory;
  }

  public Money getBalanceCoins() {
    return balance;
  }
}
