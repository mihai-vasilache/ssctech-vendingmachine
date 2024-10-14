package com.ssctech.vendingmachine.domain.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.ssctech.vendingmachine.domain.state.MachineConfiguration;

public class Money {

  public static final Money ZERO = Money.builder().build();

  //value of the map is the number of coins
  private final Map<Coin, Integer> amount;

  private Money(Map<Coin, Integer> amount) {
    this.amount = amount;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Money add(Coin coin, Integer amount) {
    return builder().add(this.amount).add(coin, amount).build();
  }

  public Money add(Money money) {
    return builder().add(this.amount).add(money.getStoredCoins()).build();
  }

  public Map<Coin, Integer> getStoredCoins() {
    return Collections.unmodifiableMap(amount);
  }

  public BigDecimal amount() {
    BigDecimal amountOfMoney = Money.toBigDecimal(0);
    for (Entry<Coin, Integer> aCoinAmount : amount.entrySet()) {
      amountOfMoney = amountOfMoney.add(aCoinAmount.getKey().coinValue().multiply(Money.toBigDecimal(aCoinAmount.getValue()))).setScale(2, RoundingMode.HALF_UP);
    }
    return amountOfMoney;
  }

  public static BigDecimal toBigDecimal(double input) {
    return BigDecimal.valueOf(input).setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount, money.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(amount);
  }

  public static class Builder {

    private Map<Coin, Integer> amount = new HashMap<>();

    public Builder add(Coin coin, Integer amountOfCoins) {
      if (!MachineConfiguration.getSupportedCoinTypes().isSupportedCoinType(coin)) {
        throw new IllegalArgumentException("Coin type " + coin + " is not supported by this machine.");
      }
      if (!amount.isEmpty()) {
        if (!amount.keySet().stream().findFirst().get().currency().equals(coin.currency())) {
          throw new IllegalArgumentException("Money class can contain only one currency type.");
        }
      }
      this.amount.compute(
          coin, (currentKey, currentValue) -> currentValue == null ? amountOfCoins : currentValue + amountOfCoins
      );
      return this;
    }

    public Builder add(Map<Coin, Integer> coins) {
      coins.forEach((coin, amount) -> add(coin, amount));
      return this;
    }

    public Money build() {
      return new Money(amount);
    }

  }

}
