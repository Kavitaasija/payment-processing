package org.pay.engine.paymentprocessing.client;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implementation of RestClient using WebClient.
 *
 * @param <T> The request type
 * @param <R> The response type
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WebClientRestClient<T, R> implements RestClient<T, R> {

  private final WebClient webClient;

  @Override
  public Mono<R> exchange(
      String url,
      HttpMethod method,
      T requestBody,
      Class<R> responseType,
      HttpHeaders headers,
      Map<String, String> queryParams) {

    log.debug("Making {} request to {}", method, url);

    WebClient.RequestBodySpec requestSpec = webClient
        .method(method)
        .uri(uriBuilder -> {
          uriBuilder.path(url);
          if (queryParams != null) {
            queryParams.forEach(uriBuilder::queryParam);
          }
          return uriBuilder.build();
        });

    if (headers != null) {
      requestSpec.headers(httpHeaders -> httpHeaders.addAll(headers));
    }

    // Set default content type if not specified
    if (requestBody != null &&
        (headers == null || !headers.containsKey(HttpHeaders.CONTENT_TYPE))) {
      requestSpec.contentType(MediaType.APPLICATION_JSON);
    }

    WebClient.RequestHeadersSpec<?> headersSpec;
    if (requestBody != null) {
      headersSpec = requestSpec.bodyValue(requestBody);
    } else {
      headersSpec = requestSpec;
    }

    return headersSpec
        .retrieve()
        .bodyToMono(responseType)
        .doOnSuccess(response -> log.debug("Received response: {}", response))
        .doOnError(error -> log.error("Error during {} request to {}: {}",
            method, url, error.getMessage()));
  }
}