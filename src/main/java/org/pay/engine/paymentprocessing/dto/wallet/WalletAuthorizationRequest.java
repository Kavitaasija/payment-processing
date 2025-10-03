package org.pay.engine.paymentprocessing.dto.wallet;

import java.math.BigDecimal;

/**
 * DTO for wallet authorization request.
 */
public class WalletAuthorizationRequest {
    
    private String merchantId;
    private String merchantReference;
    private String walletId;
    private String walletProvider;
    private String accountEmail;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String returnUrl;
    
    // Getters and setters
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getMerchantReference() {
        return merchantReference;
    }
    
    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }
    
    public String getWalletId() {
        return walletId;
    }
    
    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }
    
    public String getWalletProvider() {
        return walletProvider;
    }
    
    public void setWalletProvider(String walletProvider) {
        this.walletProvider = walletProvider;
    }
    
    public String getAccountEmail() {
        return accountEmail;
    }
    
    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getReturnUrl() {
        return returnUrl;
    }
    
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    
    @Override
    public String toString() {
        return "WalletAuthorizationRequest{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantReference='" + merchantReference + '\'' +
                ", walletId='" + walletId + '\'' +
                ", walletProvider='" + walletProvider + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                '}';
    }
}