package org.pay.engine.paymentprocessing.factory;

import lombok.extern.slf4j.Slf4j;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethod;
import org.pay.engine.paymentprocessing.model.payment.PaymentMethodType;
import org.pay.engine.paymentprocessing.service.executor.PaymentExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PaymentExecutorFactory {

    private final Map<PaymentMethodType, PaymentExecutorService> executorMap;

    @Autowired
    public PaymentExecutorFactory(List<PaymentExecutorService> executors) {
        this.executorMap = executors.stream()
                .collect(Collectors.toMap(
                        PaymentExecutorService::getPaymentMethodType,
                        Function.identity()
                ));
        
        log.info("Initialized PaymentExecutorFactory with executors for payment methods: {}", 
                executorMap.keySet());
    }

    public Optional<PaymentExecutorService> getExecutor(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            log.error("Cannot get executor for null payment method");
            return Optional.empty();
        }
        
        PaymentMethodType type = paymentMethod.getType();
        PaymentExecutorService executor = executorMap.get(type);
        
        if (executor == null) {
            log.error("No executor found for payment method type: {}", type);
            return Optional.empty();
        }
        
        return Optional.of(executor);
    }
}