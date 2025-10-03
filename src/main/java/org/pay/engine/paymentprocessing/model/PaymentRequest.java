package org.pay.engine.paymentprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;

import java.util.Objects;

@Getter
@Setter
public class PaymentRequest {
    private String id;
    private PaymentMethod paymentMethod;
    private Amount amount;
    private Customer customer;
    private Order order;
    private String description;
    private String returnUrl;
    private String merchantPaymentChargeReference;
    private String merchantId;
    
    public PaymentRequest() {
    }
    
    public PaymentRequest(String id, PaymentMethod paymentMethod, Amount amount,
                         Customer customer, Order order, String description, String returnUrl,
                         String merchantPaymentChargeReference) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.customer = customer;
        this.order = order;
        this.description = description;
        this.returnUrl = returnUrl;
        this.merchantPaymentChargeReference = merchantPaymentChargeReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return Objects.equals(id, that.id) &&
                paymentMethod == that.paymentMethod &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(order, that.order) &&
                Objects.equals(description, that.description) &&
                Objects.equals(returnUrl, that.returnUrl) &&
                Objects.equals(merchantPaymentChargeReference, that.merchantPaymentChargeReference);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, amount, customer, order, description, returnUrl,
                merchantPaymentChargeReference);
    }
    
    @Override
    public String toString() {
        return "PaymentRequest{" +
                "id='" + id + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                ", customer=" + customer +
                ", order=" + order +
                ", description='" + description + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", merchantPaymentChargeReference='" + merchantPaymentChargeReference + '\'' +
                '}';
    }
    
}