package org.pay.engine.paymentprocessing.model;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
  private PaymentStatus status;
  private String message;
  private LocalDateTime timestamp;
  private String transactionId;
  private Amount amount;
  private String errorCode;
  private String errorMessage;

  public static class PaymentResponseBuilder {
    private LocalDateTime timestamp = LocalDateTime.now();

    public PaymentResponseBuilder timestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }
  }

  public static PaymentResponse success(String id, String transactionId, Amount amount) {
    return PaymentResponse.builder()
        .status(PaymentStatus.SUCCESS)
        .message(PaymentStatus.SUCCESS.getDescription())
        .transactionId(transactionId)
        .amount(amount)
        .build();
  }

  public static PaymentResponse authorizationProcessing(String transactionId, Amount amount) {
    return PaymentResponse.builder()
        .status(PaymentStatus.AUTHORIZATION_PROCESSING)
        .message(PaymentStatus.AUTHORIZATION_PROCESSING.getDescription())
        .transactionId(transactionId)
        .amount(amount)
        .build();
  }

  public static PaymentResponse authorizationPending(String transactionId, Amount amount) {
    return PaymentResponse.builder()
        .status(PaymentStatus.AUTHORIZATION_PENDING)
        .message(PaymentStatus.AUTHORIZATION_PENDING.getDescription())
        .transactionId(transactionId)
        .amount(amount)
        .build();
  }

  public static PaymentResponse failed(String transactionId, String message) {
    return PaymentResponse.builder()
        .transactionId(transactionId)
        .status(PaymentStatus.FAILED)
        .message(message)
        .build();
  }

  public static PaymentResponse failed(String transactionId, String errorCode, String errorMessage) {
    return PaymentResponse.builder()
        .transactionId(transactionId)
        .status(PaymentStatus.FAILED)
        .message(PaymentStatus.FAILED.getDescription())
        .errorCode(errorCode)
        .errorMessage(errorMessage)
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
    PaymentResponse that = (PaymentResponse) o;
    return status == that.status &&
        Objects.equals(message, that.message) &&
        Objects.equals(timestamp, that.timestamp) &&
        Objects.equals(transactionId, that.transactionId) &&
        Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, timestamp, transactionId, amount);
  }

  @Override
  public String toString() {
    return "PaymentResponse{" +
        ", status=" + status +
        ", message='" + message + '\'' +
        ", timestamp=" + timestamp +
        ", transactionId='" + transactionId + '\'' +
        ", amount=" + amount +
        ", errorCode='" + errorCode + '\'' +
        ", errorMessage='" + errorMessage + '\'' +
        '}';
  }
}