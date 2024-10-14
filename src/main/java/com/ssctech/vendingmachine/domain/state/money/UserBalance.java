package com.ssctech.vendingmachine.domain.state.money;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

  public void withdraw(double amount) {
    double newAmount = amount;
    Money newBalance = balance;
    Map<Coin, Integer> userBalanceCoins = new TreeMap<>(newBalance.getStoredCoins());
    List<Coin> balanceCoinTypes = userBalanceCoins.keySet().stream().toList();
    Collections.reverse(balanceCoinTypes);
    boolean lastIterationExtractedCoin = false;
    while (newAmount > 0) {
      for (Coin aBallanceCoinType : balanceCoinTypes) {
        while (true) {
          if (aBallanceCoinType.coinValue().doubleValue() < newAmount) {
            newAmount = newAmount - aBallanceCoinType.coinValue().doubleValue();
            newBalance = newBalance.subtract(aBallanceCoinType);
            userBalanceCoins = new TreeMap<>(newBalance.getStoredCoins());
            balanceCoinTypes = userBalanceCoins.keySet().stream().toList();
            Collections.reverse(balanceCoinTypes);
            lastIterationExtractedCoin = true;
            continue;
          }
          break;
        }
        if (!lastIterationExtractedCoin) {
          throw new IllegalArgumentException(
              "Cannot extract amount " + amount + " from coins:" + balance.getStoredCoins());
        }
      }
    }
    balance = newBalance;
  }

  public Money getBalanceCoins() {
    return balance;
  }
}
