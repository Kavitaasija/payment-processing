package org.pay.engine.paymentprocessing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for WebClient.
 */
@Configuration
public class WebClientConfig {

  /**
   * Creates a WebClient bean.
   *
   * @return The WebClient bean
   */
  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .build();
  }
}