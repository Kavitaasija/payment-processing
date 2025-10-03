package org.pay.engine.paymentprocessing.service.executor;

import lombok.extern.slf4j.Slf4j;
import org.pay.engine.paymentprocessing.event.AuthorizationProcessEvent;
import org.pay.engine.paymentprocessing.event.AuthorizationResultEvent;
import org.pay.engine.paymentprocessing.model.payment.CardPaymentMethod;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethodType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Implementation of PaymentExecutorService for card payments.
 */
@Service
@Slf4j
public class CardPaymentExecutorService implements PaymentExecutorService {

    @Override
    public Mono<AuthorizationResultEvent> authorize(AuthorizationProcessEvent event) {
        PaymentMethod paymentMethod = event.getPaymentMethod();
        
        if (!(paymentMethod instanceof CardPaymentMethod)) {
            log.error("Invalid payment method type for CardPaymentExecutorService: {}", 
                    paymentMethod.getClass().getSimpleName());
            return Mono.just(AuthorizationResultEvent.failure(
                    event.getId(),
                    event.getMerchantId(),
                    event.getMerchantPaymentChargeReference(),
                    UUID.randomUUID().toString(),
                    event.getAmount(),
                    "INVALID_PAYMENT_METHOD",
                    "Card payment method expected but received: " + paymentMethod.getClass().getSimpleName()
            ));
        }
        
        CardPaymentMethod cardPaymentMethod = (CardPaymentMethod) paymentMethod;
        
        // Simulate card processing with external payment gateway
        log.info("Processing card payment for merchant: {}, reference: {}", 
                event.getMerchantId(), event.getMerchantPaymentChargeReference());
        
        // In a real implementation, this would call an external payment gateway
        // For now, we'll simulate a successful authorization
        String transactionId = UUID.randomUUID().toString();
        String processorReference = "CARD-" + UUID.randomUUID().toString().substring(0, 8);
        
        log.info("Card payment authorized successfully. Transaction ID: {}, Processor Reference: {}", 
                transactionId, processorReference);
        
        return Mono.just(AuthorizationResultEvent.success(
                event.getId(),
                event.getMerchantId(),
                event.getMerchantPaymentChargeReference(),
                transactionId,
                event.getAmount(),
                processorReference
        ));
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.CARD;
    }
}