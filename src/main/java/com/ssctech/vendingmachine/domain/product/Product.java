package com.ssctech.vendingmachine.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

import com.ssctech.vendingmachine.domain.money.Money;

public class Product {

  private final String name;
  private final BigDecimal price;

  public Product(String name, double price) {
    this.name = name;
    this.price = Money.toBigDecimal(price);
  }

  public boolean sameNameButDifferentPrice(Product otherProduct) {
    if (!name.equals(otherProduct.getName())) {
      return false;
    }
    return !price.equals(otherProduct.getPrice());
  }

  public String getName() {
    return name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(name, product.name) && Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price);
  }
}
