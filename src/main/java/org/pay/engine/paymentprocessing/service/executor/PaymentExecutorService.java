package org.pay.engine.paymentprocessing.service.executor;

import org.pay.engine.paymentprocessing.event.AuthorizationProcessEvent;
import org.pay.engine.paymentprocessing.event.AuthorizationResultEvent;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethodType;
import reactor.core.publisher.Mono;

/**
 * Interface for payment processor executors.
 * Implementations will handle specific payment method types.
 */
public interface PaymentExecutorService {
    
    /**
     * Authorizes a payment using the specific payment processor.
     *
     * @param event The authorization process event
     * @return A Mono emitting the authorization result event
     */
    Mono<AuthorizationResultEvent> authorize(AuthorizationProcessEvent event);
    
    /**
     * Returns the payment method type this executor handles.
     *
     * @return The payment method type
     */
    PaymentMethodType getPaymentMethodType();
    
    /**
     * Checks if this executor can handle the given payment method type.
     *
     * @param paymentMethodType The payment method type to check
     * @return true if this executor can handle the payment method type, false otherwise
     */
    default boolean canHandle(PaymentMethodType paymentMethodType) {
        return getPaymentMethodType() == paymentMethodType;
    }
}