package org.pay.engine.paymentprocessing.factory;

import java.time.Instant;
import org.pay.engine.paymentprocessing.event.PaymentRequestEvent;
import org.pay.engine.paymentprocessing.persistence.entity.PaymentRequestEntity;
import org.springframework.stereotype.Component;

/**
 * Factory for creating PaymentRequestEntity objects from PaymentRequestEvent objects.
 */
@Component
public class PaymentRequestEntityFactory {

  public PaymentRequestEntity createPaymentRequestEntity(PaymentRequestEvent event) {
    PaymentRequestEntity entity = new PaymentRequestEntity();
    entity.setId(event.getId());
    entity.setMerchantId(event.getMerchantId());
    entity.setMerchantPaymentChargeReference(event.getMerchantPaymentChargeReference());
    entity.setPaymentMethodType(event.getPaymentMethod().getType());
    entity.setAmount(event.getAmount());
    entity.setDescription(event.getDescription());
    entity.setReturnUrl(event.getReturnUrl());
    entity.setCreatedAt(event.getCreatedAt());
    entity.setUpdatedAt(Instant.now());
    return entity;
  }
}