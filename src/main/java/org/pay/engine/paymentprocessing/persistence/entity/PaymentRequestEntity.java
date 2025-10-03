package org.pay.engine.paymentprocessing.persistence.entity;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pay.engine.paymentprocessing.model.Amount;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethodType;

/**
 * Entity representing a payment request in the database.
 * Stores only essential details; does not store order or customer information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEntity {
  private String id;
  private String merchantId;
  private String merchantPaymentChargeReference;
  private PaymentMethodType paymentMethodType;
  private Amount amount;
  private String description;
  private String returnUrl;
  private Instant createdAt;
  private Instant updatedAt;

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    PaymentRequestEntity that = (PaymentRequestEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(merchantId, that.merchantId) &&
        Objects.equals(merchantPaymentChargeReference, that.merchantPaymentChargeReference) &&
        paymentMethodType == that.paymentMethodType &&
        Objects.equals(amount, that.amount) &&
        Objects.equals(description, that.description) &&
        Objects.equals(returnUrl, that.returnUrl) &&
        Objects.equals(createdAt, that.createdAt) &&
        Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantId, merchantPaymentChargeReference, paymentMethodType,
        amount, description, returnUrl, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "PaymentRequestEntity{" +
        "id='" + id + '\'' +
        ", merchantId='" + merchantId + '\'' +
        ", merchantPaymentChargeReference='" + merchantPaymentChargeReference + '\'' +
        ", paymentMethodType=" + paymentMethodType +
        ", amount=" + amount +
        ", description='" + description + '\'' +
        ", returnUrl='" + returnUrl + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}