package org.pay.engine.paymentprocessing.event;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pay.engine.paymentprocessing.model.Amount;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;

/**
 * Event representing a payment request to be processed.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEvent {
  private String id;
  private String merchantId;
  private String merchantPaymentChargeReference;
  private PaymentMethod paymentMethod;
  private Amount amount;
  private String description;
  private String returnUrl;
  private Instant createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentRequestEvent that = (PaymentRequestEvent) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(merchantId, that.merchantId) &&
        Objects.equals(merchantPaymentChargeReference, that.merchantPaymentChargeReference) &&
        paymentMethod == that.paymentMethod &&
        Objects.equals(amount, that.amount) &&
        Objects.equals(description, that.description) &&
        Objects.equals(returnUrl, that.returnUrl) &&
        Objects.equals(createdAt, that.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantId, merchantPaymentChargeReference, paymentMethod,
        amount, description, returnUrl, createdAt);
  }

  @Override
  public String toString() {
    return "PaymentRequestEvent{" +
        "id='" + id + '\'' +
        ", merchantId='" + merchantId + '\'' +
        ", merchantPaymentChargeReference='" + merchantPaymentChargeReference + '\'' +
        ", paymentMethod=" + paymentMethod +
        ", amount=" + amount +
        ", description='" + description + '\'' +
        ", returnUrl='" + returnUrl + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}