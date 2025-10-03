package org.pay.engine.paymentprocessing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.pay.engine.paymentprocessing.event.AuthorizationProcessEvent;
import org.pay.engine.paymentprocessing.event.AuthorizationResultEvent;
import org.pay.engine.paymentprocessing.event.PaymentRequestEvent;
import org.pay.engine.paymentprocessing.factory.PaymentExecutorFactory;
import org.pay.engine.paymentprocessing.factory.PaymentRequestEntityFactory;
import org.pay.engine.paymentprocessing.factory.PaymentRequestFactory;
import org.pay.engine.paymentprocessing.model.PaymentRequest;
import org.pay.engine.paymentprocessing.model.PaymentResponse;
import org.pay.engine.paymentprocessing.model.PaymentStatus;
import org.pay.engine.paymentprocessing.persistence.entity.PaymentRequestEntity;
import org.pay.engine.paymentprocessing.persistence.repository.PaymentRepository;
import org.pay.engine.paymentprocessing.service.executor.PaymentExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentRequestEntityFactory paymentRequestEntityFactory;
  private final PaymentExecutorFactory paymentExecutorFactory;

  @Autowired
  public PaymentService(
      PaymentRepository paymentRepository,
      PaymentRequestFactory paymentRequestFactory,
      PaymentRequestEntityFactory paymentRequestEntityFactory,
      PaymentExecutorFactory paymentExecutorFactory) {
    this.paymentRepository = paymentRepository;
    this.paymentRequestEntityFactory = paymentRequestEntityFactory;
    this.paymentExecutorFactory = paymentExecutorFactory;
  }

  public PaymentResponse processPayment(PaymentRequest paymentRequest, PaymentRequestEvent paymentRequestEvent) {
    PaymentRequestEntity entity = paymentRequestEntityFactory.createPaymentRequestEntity(paymentRequestEvent);
    paymentRepository.save(entity);
    String transactionId = UUID.randomUUID().toString();
    AuthorizationProcessEvent authEvent = AuthorizationProcessEvent.fromPaymentRequestEvent(paymentRequestEvent);
    Optional<PaymentExecutorService> executorOpt =
        paymentExecutorFactory.getExecutor(paymentRequest.getPaymentMethod());

    if (executorOpt.isPresent()) {
      PaymentExecutorService executor = executorOpt.get();
      log.info("Processing payment with executor for payment method type: {}",
          paymentRequest.getPaymentMethod().getType());
      Mono<AuthorizationResultEvent> resultMono = executor.authorize(authEvent);
      resultMono.subscribe(
          result -> {
            log.info("Payment processed with status: {}", result.getStatus());
          },
          error -> {
            log.error("Error processing payment: {}", error.getMessage(), error);
          }
      );
      return PaymentResponse.authorizationProcessing(
          transactionId,
          paymentRequest.getAmount()
      );
    } else {
      log.error("No payment executor found for payment method type: {}",
          paymentRequest.getPaymentMethod().getType());
      return PaymentResponse.builder()
          .transactionId(transactionId)
          .amount(paymentRequest.getAmount())
          .status(PaymentStatus.FAILED)
          .errorCode("UNSUPPORTED_PAYMENT_METHOD")
          .errorMessage("Unsupported payment method type: " + paymentRequest.getPaymentMethod().getType())
          .build();
    }
  }

  public Optional<PaymentRequestEntity> getPaymentRequestById(String id) {
    return paymentRepository.findById(id);
  }

  public List<PaymentRequestEntity> getPaymentRequestsByMerchantId(String merchantId) {
    return paymentRepository.findByMerchantId(merchantId);
  }

  public Optional<PaymentRequestEntity> getPaymentRequestByMerchantReference(
      String merchantId, String merchantPaymentChargeReference) {
    return paymentRepository.findByMerchantIdAndMerchantPaymentChargeReference(
        merchantId, merchantPaymentChargeReference);
  }
}