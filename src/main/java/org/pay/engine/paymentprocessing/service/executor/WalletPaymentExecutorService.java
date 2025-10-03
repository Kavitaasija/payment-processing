package org.pay.engine.paymentprocessing.service.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pay.engine.paymentprocessing.client.wallet.WalletProcessorClient;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationRequest;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationResponse;
import org.pay.engine.paymentprocessing.event.AuthorizationProcessEvent;
import org.pay.engine.paymentprocessing.event.AuthorizationResultEvent;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethodType;
import org.pay.engine.paymentprocessing.model.payment.WalletPaymentMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Implementation of PaymentExecutorService for wallet payments.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WalletPaymentExecutorService implements PaymentExecutorService {

    private final WalletProcessorClient walletProcessorClient;

    @Override
    public Mono<AuthorizationResultEvent> authorize(AuthorizationProcessEvent event) {
        PaymentMethod paymentMethod = event.getPaymentMethod();
        
        // Validate that this is a wallet payment method
        if (!(paymentMethod instanceof WalletPaymentMethod)) {
            log.error("Invalid payment method type for WalletPaymentExecutorService: {}", 
                    paymentMethod.getClass().getSimpleName());
            return Mono.just(AuthorizationResultEvent.failure(
                    event.getId(),
                    event.getMerchantId(),
                    event.getMerchantPaymentChargeReference(),
                    UUID.randomUUID().toString(),
                    event.getAmount(),
                    "INVALID_PAYMENT_METHOD",
                    "Wallet payment method expected but received: " + paymentMethod.getClass().getSimpleName()
            ));
        }
        
        WalletPaymentMethod walletPaymentMethod = (WalletPaymentMethod) paymentMethod;
        
        // Create wallet authorization request
        WalletAuthorizationRequest request = new WalletAuthorizationRequest();
        request.setMerchantId(event.getMerchantId());
        request.setMerchantReference(event.getMerchantPaymentChargeReference());
        request.setWalletId(walletPaymentMethod.getWalletId());
        request.setWalletProvider(walletPaymentMethod.getWalletProvider());
        request.setAccountEmail(walletPaymentMethod.getAccountEmail());
        request.setAmount(event.getAmount().getValue());
        request.setCurrency(event.getAmount().getCurrency().getCode());
        request.setDescription(event.getDescription());
        request.setReturnUrl(event.getReturnUrl());
        
        log.info("Processing wallet payment for merchant: {}, reference: {}, provider: {}",
                event.getMerchantId(),
                event.getMerchantPaymentChargeReference(),
                walletPaymentMethod.getWalletProvider());
        
        // Call the wallet processor client to authorize the payment
        return walletProcessorClient.authorizePayment(request)
                .map(response -> {
                    if (response.isSuccess()) {
                        log.info("Wallet payment authorized successfully. Transaction ID: {}, Processor Reference: {}",
                                response.getTransactionId(), response.getProcessorReference());
                        
                        return AuthorizationResultEvent.success(
                                event.getId(),
                                event.getMerchantId(),
                                event.getMerchantPaymentChargeReference(),
                                response.getTransactionId(),
                                event.getAmount(),
                                response.getProcessorReference()
                        );
                    } else {
                        log.error("Wallet payment authorization failed. Error: {}, Message: {}",
                                response.getErrorCode(), response.getErrorMessage());
                        
                        return AuthorizationResultEvent.failure(
                                event.getId(),
                                event.getMerchantId(),
                                event.getMerchantPaymentChargeReference(),
                                response.getTransactionId() != null ? response.getTransactionId() : UUID.randomUUID().toString(),
                                event.getAmount(),
                                response.getErrorCode() != null ? response.getErrorCode() : "PROCESSOR_ERROR",
                                response.getErrorMessage() != null ? response.getErrorMessage() : "Error processing wallet payment"
                        );
                    }
                })
                .onErrorResume(error -> {
                    log.error("Error during wallet payment processing: {}", error.getMessage());
                    
                    return Mono.just(AuthorizationResultEvent.failure(
                            event.getId(),
                            event.getMerchantId(),
                            event.getMerchantPaymentChargeReference(),
                            UUID.randomUUID().toString(),
                            event.getAmount(),
                            "PROCESSOR_ERROR",
                            "Error processing wallet payment: " + error.getMessage()
                    ));
                });
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.WALLET;
    }
}