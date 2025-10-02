package org.pay.engine.paymentprocessing.model;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {
    private BigDecimal value;
    private Currency currency;
    
    public Amount() {
    }
    
    public Amount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }
    
    public Amount(BigDecimal value, String currencyCode) {
        this.value = value;
        this.currency = Currency.findByCode(currencyCode);
    }

    public void setCurrency(String currencyCode) {
        this.currency = Currency.findByCode(currencyCode);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) &&
                Objects.equals(currency, amount.currency);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
    
    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }
    
    /**
     * Format the amount with currency symbol
     * @return Formatted amount with currency symbol
     */
    public String getFormattedAmount() {
        if (currency == null) {
            return value.toString();
        }
        return currency.getSymbol() + value.toString();
    }
}