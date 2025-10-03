package org.pay.engine.paymentprocessing.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for wallet processor.
 */
@Configuration
@ConfigurationProperties(prefix = "wallet.processor")
@Getter
@Setter
public class WalletProcessorConfig {
    
    /**
     * Base URL for the wallet processor API.
     */
    private String baseUrl;
    
    /**
     * API key for authentication with the wallet processor.
     */
    private String apiKey;
    
    /**
     * Timeout in milliseconds for requests to the wallet processor.
     */
    private int timeoutMs = 5000;
    
    /**
     * Maximum number of retries for failed requests.
     */
    private int maxRetries = 3;
}