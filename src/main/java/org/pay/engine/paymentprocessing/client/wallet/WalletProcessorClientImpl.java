package org.pay.engine.paymentprocessing.client.wallet;

import lombok.extern.slf4j.Slf4j;
import org.pay.engine.paymentprocessing.client.WebClientRestClient;
import org.pay.engine.paymentprocessing.config.WalletProcessorConfig;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationRequest;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implementation of WalletProcessorClient for interacting with wallet payment processors.
 */
@Component
@Slf4j
public class WalletProcessorClientImpl
    extends WebClientRestClient<WalletAuthorizationRequest, WalletAuthorizationResponse>
    implements WalletProcessorClient {

  private final WalletProcessorConfig config;
  private static final String AUTHORIZATION_ENDPOINT = "/api/v1/wallet/authorize";

  public WalletProcessorClientImpl(WebClient webClient, WalletProcessorConfig config) {
    super(webClient);
    this.config = config;
  }

  @Override
  public Mono<WalletAuthorizationResponse> authorizePayment(WalletAuthorizationRequest request) {
    log.info("Authorizing wallet payment for merchant: {}, reference: {}, provider: {}",
        request.getMerchantId(),
        request.getMerchantReference(),
        request.getWalletProvider());

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + config.getApiKey());
    headers.set("X-Wallet-Provider", request.getWalletProvider());

    return post(
        config.getBaseUrl() + AUTHORIZATION_ENDPOINT,
        request,
        WalletAuthorizationResponse.class,
        headers
    ).doOnSuccess(response -> {
      if (response.isSuccess()) {
        log.info("Wallet payment authorized successfully. Transaction ID: {}, Processor Reference: {}",
            response.getTransactionId(), response.getProcessorReference());
      } else {
        log.error("Wallet payment authorization failed. Error: {}, Message: {}",
            response.getErrorCode(), response.getErrorMessage());
      }
    }).doOnError(error ->
        log.error("Error during wallet payment authorization: {}", error.getMessage())
    );
  }
}