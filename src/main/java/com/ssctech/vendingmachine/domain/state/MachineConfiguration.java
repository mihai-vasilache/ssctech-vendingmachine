package com.ssctech.vendingmachine.domain.state;

import com.ssctech.vendingmachine.domain.state.money.AllowedMachineCoinTypes;

public class MachineConfiguration {

  private static AllowedMachineCoinTypes allowedMachineCoinTypes;

  public static void configure(AllowedMachineCoinTypes allowedMachineCoinTypes) {
    if (MachineConfiguration.allowedMachineCoinTypes != null) {
      throw new IllegalStateException("The machine can be configured only once.");
    }
    MachineConfiguration.allowedMachineCoinTypes = allowedMachineCoinTypes;
  }

  public static AllowedMachineCoinTypes getSupportedCoinTypes() {
    if (allowedMachineCoinTypes == null) {
      throw new IllegalStateException(
          "Vending machine is not configured yet. Supported coin types must be set at startup.");
    }
    return allowedMachineCoinTypes;
  }

}
