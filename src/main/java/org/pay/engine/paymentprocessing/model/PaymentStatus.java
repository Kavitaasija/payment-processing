package org.pay.engine.paymentprocessing.model;

import lombok.Getter;

@Getter
public enum PaymentStatus {
  AUTHORIZATION_PROCESSING("Payment authorization is being processed"),
  AUTHORIZATION_PENDING("Payment authorization is pending"),
  CAPTURE_PENDING("Payment capture is pending"),
  CAPTURE_PROCESSING("Payment capture is being processed"),
  CAPTURED("Payment has been captured successfully"),
  REFUND_PROCESSING("Refund is being processed"),
  REFUNDED("Payment has been refunded"),
  VOID_PROCESSING("Void operation is being processed"),
  VOIDED("Payment has been voided"),
  SUCCESS("Payment processed successfully"),
  FAILED("Payment processing failed"),
  DISCARDED("Payment has been discarded");

  private final String description;

  PaymentStatus(String description) {
    this.description = description;
  }

}