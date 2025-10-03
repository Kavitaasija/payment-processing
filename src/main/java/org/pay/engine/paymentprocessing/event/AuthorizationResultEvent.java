package org.pay.engine.paymentprocessing.event;

import java.time.Instant;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pay.engine.paymentprocessing.model.Amount;
import org.pay.engine.paymentprocessing.model.PaymentStatus;

/**
 * Event representing the result of a payment authorization process.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationResultEvent {
  private String id;
  private String merchantId;
  private String merchantPaymentChargeReference;
  private String transactionId;
  private PaymentStatus status;
  private Amount amount;
  private String processorReference;
  private String errorCode;
  private String errorMessage;
  private Instant processedAt;

  public static AuthorizationResultEvent success(
      String id,
      String merchantId,
      String merchantPaymentChargeReference,
      String transactionId,
      Amount amount,
      String processorReference) {
    return AuthorizationResultEvent.builder()
        .id(id)
        .merchantId(merchantId)
        .merchantPaymentChargeReference(merchantPaymentChargeReference)
        .transactionId(transactionId)
        .status(PaymentStatus.AUTHORIZATION_PROCESSING)
        .amount(amount)
        .processorReference(processorReference)
        .processedAt(Instant.now())
        .build();
  }

  public static AuthorizationResultEvent failure(
      String id,
      String merchantId,
      String merchantPaymentChargeReference,
      String transactionId,
      Amount amount,
      String errorCode,
      String errorMessage) {
    return AuthorizationResultEvent.builder()
        .id(id)
        .merchantId(merchantId)
        .merchantPaymentChargeReference(merchantPaymentChargeReference)
        .transactionId(transactionId)
        .status(PaymentStatus.FAILED)
        .amount(amount)
        .errorCode(errorCode)
        .errorMessage(errorMessage)
        .processedAt(Instant.now())
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
    AuthorizationResultEvent that = (AuthorizationResultEvent) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(merchantId, that.merchantId) &&
        Objects.equals(merchantPaymentChargeReference, that.merchantPaymentChargeReference) &&
        Objects.equals(transactionId, that.transactionId) &&
        status == that.status &&
        Objects.equals(amount, that.amount) &&
        Objects.equals(processorReference, that.processorReference) &&
        Objects.equals(errorCode, that.errorCode) &&
        Objects.equals(errorMessage, that.errorMessage) &&
        Objects.equals(processedAt, that.processedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, merchantId, merchantPaymentChargeReference, transactionId, status,
        amount, processorReference, errorCode, errorMessage, processedAt);
  }

  @Override
  public String toString() {
    return "AuthorizationResultEvent{" +
        "id='" + id + '\'' +
        ", merchantId='" + merchantId + '\'' +
        ", merchantPaymentChargeReference='" + merchantPaymentChargeReference + '\'' +
        ", transactionId='" + transactionId + '\'' +
        ", status=" + status +
        ", amount=" + amount +
        ", processorReference='" + processorReference + '\'' +
        ", errorCode='" + errorCode + '\'' +
        ", errorMessage='" + errorMessage + '\'' +
        ", processedAt=" + processedAt +
        '}';
  }
}