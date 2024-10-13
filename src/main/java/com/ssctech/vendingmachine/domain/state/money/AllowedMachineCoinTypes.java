package com.ssctech.vendingmachine.domain.state.money;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.ssctech.vendingmachine.domain.money.Coin;

public class AllowedMachineCoinTypes {

  private Set<Coin> coinTypes;

  private AllowedMachineCoinTypes(Set<Coin> coinTypes) {
    this.coinTypes = coinTypes;
  }

  public boolean isSupportedCoinType(Coin coin) {
    return coinTypes.contains(coin);
  }

  public Currency getCurrency() {
    if (coinTypes.isEmpty()) {
      throw new IllegalStateException("Allowed coin types are not configured yet");
    }
    return coinTypes.stream().findFirst().get().currency();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Set<Coin> coinTypes = new HashSet<>();

    public Builder addCoinType(Coin coin) {
      if (!coinTypes.isEmpty()) {
        Currency currentCurrency = coinTypes.stream().findFirst().get().currency();
        if (!currentCurrency.equals(coin.currency())) {
          throw new IllegalArgumentException(
              "The machine operates with only one currency: " + currentCurrency.getCurrencyCode());
        }
      }
      this.coinTypes.add(coin);
      return this;
    }

    public AllowedMachineCoinTypes build() {
      return new AllowedMachineCoinTypes(coinTypes);
    }

  }

}

