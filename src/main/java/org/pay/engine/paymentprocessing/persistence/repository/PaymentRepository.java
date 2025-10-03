package org.pay.engine.paymentprocessing.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.pay.engine.paymentprocessing.persistence.entity.PaymentRequestEntity;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing payment request entities.
 */
@Repository
public interface PaymentRepository {

  /**
   * Saves a payment request entity.
   *
   * @param paymentRequestEntity The payment request entity to save
   * @return The saved payment request entity
   */
  PaymentRequestEntity save(PaymentRequestEntity paymentRequestEntity);

  /**
   * Finds a payment request entity by its ID.
   *
   * @param id The ID of the payment request entity
   * @return An Optional containing the payment request entity if found, or empty if not found
   */
  Optional<PaymentRequestEntity> findById(String id);

  /**
   * Finds all payment request entities for a merchant.
   *
   * @param merchantId The merchant ID
   * @return A list of payment request entities
   */
  List<PaymentRequestEntity> findByMerchantId(String merchantId);

  /**
   * Finds a payment request entity by its merchant payment charge reference.
   *
   * @param merchantId                     The merchant ID
   * @param merchantPaymentChargeReference The merchant payment charge reference
   * @return An Optional containing the payment request entity if found, or empty if not found
   */
  Optional<PaymentRequestEntity> findByMerchantIdAndMerchantPaymentChargeReference(
      String merchantId, String merchantPaymentChargeReference);
}