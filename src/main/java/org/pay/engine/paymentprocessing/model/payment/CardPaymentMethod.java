package org.pay.engine.paymentprocessing.model.payment;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPaymentMethod extends PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    
    public CardPaymentMethod() {
        super(PaymentMethodType.CARD);
    }
    
    public CardPaymentMethod(String cardNumber, String cardHolderName, 
                            String expiryMonth, String expiryYear, String cvv) {
        super(PaymentMethodType.CARD);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardPaymentMethod that = (CardPaymentMethod) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardHolderName, that.cardHolderName) &&
                Objects.equals(expiryMonth, that.expiryMonth) &&
                Objects.equals(expiryYear, that.expiryYear) &&
                Objects.equals(cvv, that.cvv);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolderName, expiryMonth, expiryYear, cvv);
    }
    
    @Override
    public String toString() {
        return "CardPaymentMethod{" +
                "cardNumber='" + maskCardNumber() + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", expiryMonth='" + expiryMonth + '\'' +
                ", expiryYear='" + expiryYear + '\'' +
                ", cvv='***'" +
                '}';
    }
    
    private String maskCardNumber() {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < cardNumber.length() - 4; i++) {
            masked.append("*");
        }
        masked.append(lastFourDigits);
        
        return masked.toString();
    }
}