package org.pay.engine.paymentprocessing.model.payment;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletPaymentMethod extends PaymentMethod {
    private String walletId;
    private String walletProvider;
    private String accountEmail;
    
    public WalletPaymentMethod() {
        super(PaymentMethodType.WALLET);
    }
    
    public WalletPaymentMethod(String walletId, String walletProvider, String accountEmail) {
        super(PaymentMethodType.WALLET);
        this.walletId = walletId;
        this.walletProvider = walletProvider;
        this.accountEmail = accountEmail;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletPaymentMethod that = (WalletPaymentMethod) o;
        return Objects.equals(walletId, that.walletId) &&
                Objects.equals(walletProvider, that.walletProvider) &&
                Objects.equals(accountEmail, that.accountEmail);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(walletId, walletProvider, accountEmail);
    }
    
    @Override
    public String toString() {
        return "WalletPaymentMethod{" +
                "walletId='" + walletId + '\'' +
                ", walletProvider='" + walletProvider + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                '}';
    }
}