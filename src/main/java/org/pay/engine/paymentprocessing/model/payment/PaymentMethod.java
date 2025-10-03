package org.pay.engine.paymentprocessing.model.payment;

import lombok.Getter;
import lombok.Setter;

/**
 * Base abstract class for all payment methods
 */

@Getter
@Setter
public abstract class PaymentMethod {
  private PaymentMethodType type;

  public PaymentMethod(PaymentMethodType type) {
    this.type = type;
  }
}