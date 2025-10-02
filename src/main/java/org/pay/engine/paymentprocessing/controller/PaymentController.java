package org.pay.engine.paymentprocessing.controller;

import org.pay.engine.paymentprocessing.model.PaymentRequest;
import org.pay.engine.paymentprocessing.model.PaymentResponse;
import org.pay.engine.paymentprocessing.model.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for payment processing operations
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    /**
     * Initiates a payment transaction (checkout)
     * 
     * @param paymentRequest The payment request details
     * @return A response containing the payment status and transaction details
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        // Generate a unique transaction ID
        String transactionId = UUID.randomUUID().toString();
        
        // In a real implementation, this would call a service to process the payment
        // For now, we'll just return a response with AUTHORIZATION_PROCESSING status
        
        PaymentResponse response = PaymentResponse.authorizationProcessing(
            paymentRequest.getId(),
            transactionId,
            paymentRequest.getAmount()
        );
        
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}