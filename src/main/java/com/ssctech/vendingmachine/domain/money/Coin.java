package com.ssctech.vendingmachine.domain.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

//generic name for a coin or banknote
public class Coin implements Comparable<Coin> {

  public enum Currencies {
    RON(Currency.getInstance("RON")),
    EUR(Currency.getInstance("EUR"));

    private Currency currency;

    Currencies(Currency currency) {
      this.currency = currency;
    }

    public Currency currency() {
      return currency;
    }
  }

  private final Currency currency;
  private final BigDecimal coinValue;

  public Coin(Currencies currency, double coinValue) {
    this(currency.currency(), coinValue);
  }

  public Coin(Currency currency, double coinValue) {
    this.currency = currency;
    this.coinValue = Money.toBigDecimal(coinValue);
  }

  public Currency currency() {
    return currency;
  }

  public BigDecimal coinValue() {
    return coinValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coin coin = (Coin) o;
    return Objects.equals(currency, coin.currency) && Objects.equals(coinValue, coin.coinValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, coinValue);
  }

  @Override
  public int compareTo(Coin otherCoin) {
    return this.coinValue.compareTo(otherCoin.coinValue);
  }

  @Override
  public String toString() {
    return "Coin currency=" + currency +
        ", Coin Value=" + coinValue;
  }
}
