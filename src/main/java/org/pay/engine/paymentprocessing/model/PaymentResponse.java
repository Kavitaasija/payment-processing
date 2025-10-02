package org.pay.engine.paymentprocessing.model;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String id;
    private PaymentStatus status;
    private String message;
    private LocalDateTime timestamp;
    private String transactionId;
    private Amount amount;
    
    public PaymentResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public PaymentResponse(String id, PaymentStatus status, String message, String transactionId, Amount amount) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.transactionId = transactionId;
        this.amount = amount;
    }
    
    public static PaymentResponse success(String id, String transactionId, Amount amount) {
        return new PaymentResponse(id, PaymentStatus.SUCCESS, PaymentStatus.SUCCESS.getDescription(), transactionId, amount);
    }
    
    public static PaymentResponse authorizationProcessing(String id, String transactionId, Amount amount) {
        return new PaymentResponse(id, PaymentStatus.AUTHORIZATION_PROCESSING, PaymentStatus.AUTHORIZATION_PROCESSING.getDescription(), transactionId, amount);
    }
    
    public static PaymentResponse authorizationPending(String id, String transactionId, Amount amount) {
        return new PaymentResponse(id, PaymentStatus.AUTHORIZATION_PENDING, PaymentStatus.AUTHORIZATION_PENDING.getDescription(), transactionId, amount);
    }
    
    public static PaymentResponse failed(String id, String message) {
        return new PaymentResponse(id, PaymentStatus.FAILED, message, null, null);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentResponse that = (PaymentResponse) o;
        return Objects.equals(id, that.id) &&
                status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(amount, that.amount);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, status, message, timestamp, transactionId, amount);
    }
    
    @Override
    public String toString() {
        return "PaymentResponse{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                '}';
    }
}