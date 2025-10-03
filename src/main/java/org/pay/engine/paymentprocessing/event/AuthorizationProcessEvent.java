package org.pay.engine.paymentprocessing.event;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pay.engine.paymentprocessing.model.Amount;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;

/**
 * Event representing a payment authorization request to be processed.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationProcessEvent {
  private String id;
  private String merchantId;
  private String merchantPaymentChargeReference;
  private PaymentMethod paymentMethod;
  private Amount amount;
  private String description;
  private String returnUrl;
  private Instant createdAt;

  /**
   * Factory method to create an AuthorizationProcessEvent from a PaymentRequestEvent
   *
   * @param paymentRequestEvent The payment request event
   * @return A new AuthorizationProcessEvent
   */
  public static AuthorizationProcessEvent fromPaymentRequestEvent(PaymentRequestEvent paymentRequestEvent) {
    return AuthorizationProcessEvent.builder()
        .id(paymentRequestEvent.getId())
        .merchantId(paymentRequestEvent.getMerchantId())
        .merchantPaymentChargeReference(paymentRequestEvent.getMerchantPaymentChargeReference())
        .paymentMethod(paymentRequestEvent.getPaymentMethod())
        .amount(paymentRequestEvent.getAmount())
        .description(paymentRequestEvent.getDescription())
        .returnUrl(paymentRequestEvent.getReturnUrl())
        .createdAt(paymentRequestEvent.getCreatedAt())
        .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationProcessEvent that = (AuthorizationProcessEvent) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(merchantId, that.merchantId) &&
        Objects.equals(merchantPaymentChargeReference, that.merchantPaymentChargeReference) &&
        Objects.equals(paymentMethod, that.paymentMethod) &&
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
    return "AuthorizationProcessEvent{" +
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