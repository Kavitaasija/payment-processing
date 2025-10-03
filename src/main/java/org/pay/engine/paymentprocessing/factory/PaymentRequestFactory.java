package org.pay.engine.paymentprocessing.factory;

import java.time.Instant;
import org.pay.engine.paymentprocessing.event.PaymentRequestEvent;
import org.pay.engine.paymentprocessing.model.PaymentRequest;
import org.springframework.stereotype.Component;

/**
 * Factory for creating PaymentRequestEvent objects from PaymentRequest objects.
 */
@Component
public class PaymentRequestFactory {

  /**
   * Creates a PaymentRequestEvent from a PaymentRequest and merchant information.
   *
   * @param paymentRequest The payment request
   * @param merchantId     The merchant ID
   * @return A new PaymentRequestEvent
   */
  public PaymentRequestEvent createPaymentRequestEvent(PaymentRequest paymentRequest, String merchantId) {
    PaymentRequestEvent event = new PaymentRequestEvent();
    event.setId(paymentRequest.getId());
    event.setMerchantId(merchantId);
    event.setMerchantPaymentChargeReference(paymentRequest.getMerchantPaymentChargeReference());
    event.setPaymentMethod(paymentRequest.getPaymentMethod());
    event.setAmount(paymentRequest.getAmount());
    event.setDescription(paymentRequest.getDescription());
    event.setReturnUrl(paymentRequest.getReturnUrl());
    event.setCreatedAt(Instant.now());
    return event;
  }
}