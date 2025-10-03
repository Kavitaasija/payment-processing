package org.pay.engine.paymentprocessing.controller;

import org.pay.engine.paymentprocessing.event.PaymentRequestEvent;
import org.pay.engine.paymentprocessing.factory.PaymentRequestFactory;
import org.pay.engine.paymentprocessing.model.PaymentRequest;
import org.pay.engine.paymentprocessing.model.PaymentResponse;
import org.pay.engine.paymentprocessing.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentService paymentService;
  private final PaymentRequestFactory paymentRequestFactory;

  public PaymentController(PaymentService paymentService, PaymentRequestFactory paymentRequestFactory) {
    this.paymentService = paymentService;
    this.paymentRequestFactory = paymentRequestFactory;
  }

  @PostMapping
  public ResponseEntity<PaymentResponse> initiatePayment(
      @RequestBody PaymentRequest paymentRequest,
      @RequestHeader("X-Merchant-ID") String merchantId) {

    PaymentRequestEvent paymentRequestEvent =
        paymentRequestFactory.createPaymentRequestEvent(paymentRequest, merchantId);
    PaymentResponse response = paymentService.processPayment(paymentRequest, paymentRequestEvent);

    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
  }
}