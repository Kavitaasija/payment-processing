package org.pay.engine.paymentprocessing.client.wallet;

import org.pay.engine.paymentprocessing.client.RestClient;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationRequest;
import org.pay.engine.paymentprocessing.dto.wallet.WalletAuthorizationResponse;
import reactor.core.publisher.Mono;

/**
 * Client interface for interacting with wallet payment processors.
 */
public interface WalletProcessorClient extends RestClient<WalletAuthorizationRequest, WalletAuthorizationResponse> {

  /**
   * Authorizes a payment with the wallet provider.
   *
   * @param request The wallet authorization request
   * @return A Mono containing the wallet authorization response
   */
  Mono<WalletAuthorizationResponse> authorizePayment(WalletAuthorizationRequest request);
}