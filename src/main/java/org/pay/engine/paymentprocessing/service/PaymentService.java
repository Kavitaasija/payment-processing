package org.pay.engine.paymentprocessing.service;

import org.pay.engine.paymentprocessing.event.PaymentRequestEvent;
import org.pay.engine.paymentprocessing.factory.PaymentRequestEntityFactory;
import org.pay.engine.paymentprocessing.factory.PaymentRequestFactory;
import org.pay.engine.paymentprocessing.model.PaymentRequest;
import org.pay.engine.paymentprocessing.model.PaymentResponse;
import org.pay.engine.paymentprocessing.persistence.entity.PaymentRequestEntity;
import org.pay.engine.paymentprocessing.persistence.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for handling payment operations.
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
  private final PaymentRequestEntityFactory paymentRequestEntityFactory;

    @Autowired
    public PaymentService(
            PaymentRepository paymentRepository,
            PaymentRequestFactory paymentRequestFactory,
            PaymentRequestEntityFactory paymentRequestEntityFactory) {
        this.paymentRepository = paymentRepository;
      this.paymentRequestEntityFactory = paymentRequestEntityFactory;
    }

    /**
     * Processes a payment request.
     *
     * @param paymentRequest The payment request to process
     * @param paymentRequestEvent
     * @return A payment response
     */
    public PaymentResponse processPayment(PaymentRequest paymentRequest, PaymentRequestEvent paymentRequestEvent) {
        String transactionId = UUID.randomUUID().toString();
      PaymentRequestEntity entity = paymentRequestEntityFactory.createPaymentRequestEntity(paymentRequestEvent);
        paymentRepository.save(entity);
        return PaymentResponse.authorizationProcessing(
            paymentRequest.getId(),
            transactionId,
            paymentRequest.getAmount()
        );
    }

    /**
     * Retrieves a payment request by ID.
     *
     * @param id The payment request ID
     * @return An Optional containing the payment request entity if found, or empty if not found
     */
    public Optional<PaymentRequestEntity> getPaymentRequestById(String id) {
        return paymentRepository.findById(id);
    }

    /**
     * Retrieves all payment requests for a merchant.
     *
     * @param merchantId The merchant ID
     * @return A list of payment request entities
     */
    public List<PaymentRequestEntity> getPaymentRequestsByMerchantId(String merchantId) {
        return paymentRepository.findByMerchantId(merchantId);
    }

    /**
     * Retrieves a payment request by its merchant payment charge reference.
     *
     * @param merchantId The merchant ID
     * @param merchantPaymentChargeReference The merchant payment charge reference
     * @return An Optional containing the payment request entity if found, or empty if not found
     */
    public Optional<PaymentRequestEntity> getPaymentRequestByMerchantReference(
            String merchantId, String merchantPaymentChargeReference) {
        return paymentRepository.findByMerchantIdAndMerchantPaymentChargeReference(
                merchantId, merchantPaymentChargeReference);
    }
}