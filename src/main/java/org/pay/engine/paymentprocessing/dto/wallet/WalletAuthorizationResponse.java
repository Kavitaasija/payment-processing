package org.pay.engine.paymentprocessing.dto.wallet;

import java.math.BigDecimal;

/**
 * DTO for wallet authorization response.
 */
public class WalletAuthorizationResponse {
    
    private String transactionId;
    private String status;
    private String processorReference;
    private BigDecimal amount;
    private String currency;
    private String errorCode;
    private String errorMessage;
    
    // Getters and setters
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getProcessorReference() {
        return processorReference;
    }
    
    public void setProcessorReference(String processorReference) {
        this.processorReference = processorReference;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    /**
     * Checks if the authorization was successful.
     *
     * @return true if the authorization was successful, false otherwise
     */
    public boolean isSuccess() {
        return "AUTHORIZED".equals(status);
    }
    
    @Override
    public String toString() {
        return "WalletAuthorizationResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                ", processorReference='" + processorReference + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}