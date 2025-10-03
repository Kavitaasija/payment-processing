package org.pay.engine.paymentprocessing.model;

import lombok.Getter;

@Getter
public enum Currency {
  USD("US Dollar", "$", "USD"),
  EUR("Euro", "€", "EUR"),
  GBP("British Pound", "£", "GBP"),
  JPY("Japanese Yen", "¥", "JPY"),
  INR("Indian Rupee", "₹", "INR"),
  CAD("Canadian Dollar", "$", "CAD"),
  AUD("Australian Dollar", "$", "AUD"),
  SGD("Singapore Dollar", "$", "SGD"),
  CHF("Swiss Franc", "Fr", "CHF"),
  CNY("Chinese Yuan", "¥", "CNY");

  private final String displayName;
  private final String symbol;
  private final String code;

  Currency(String displayName, String symbol, String code) {
    this.displayName = displayName;
    this.symbol = symbol;
    this.code = code;
  }

  /**
   * Get a formatted string representation of the amount in this currency
   *
   * @param amount The amount to format
   * @return A formatted string with the currency symbol and amount
   */
  public String format(double amount) {
    return symbol + amount;
  }

  /**
   * Find a currency by its ISO code
   *
   * @param code The ISO currency code (e.g., "USD", "EUR")
   * @return The corresponding Currency enum value, or null if not found
   */
  public static Currency findByCode(String code) {
    if (code == null) {
      return null;
    }

    for (Currency currency : Currency.values()) {
      if (currency.getCode().equalsIgnoreCase(code)) {
        return currency;
      }
    }

    return null;
  }
}