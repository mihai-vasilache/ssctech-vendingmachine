package com.ssctech.vendingmachine.domain.state.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ssctech.vendingmachine.domain.money.Coin;
import com.ssctech.vendingmachine.domain.money.Money;

public class UserBalance {

  private static UserBalance soleInstance = new UserBalance();

  private BigDecimal balance = Money.toBigDecimal(0);

  private UserBalance() {
  }

  public static UserBalance instance() {
    return soleInstance;
  }

  public UserBalance add(Coin coin, Integer amount) {
    this.balance = balance.add(coin.coinValue().multiply(Money.toBigDecimal(amount)).setScale(2, RoundingMode.HALF_UP));
    MoneyInventoryHeld.instance().add(coin, amount);
    return this;
  }

  public void resetBalanceToZero() {
    this.balance = Money.toBigDecimal(0);
  }

  public void withdraw(double amount) {
    this.balance = this.balance.subtract(Money.toBigDecimal(amount));
  }

  public BigDecimal getBalance() {
    return balance;
  }
}
