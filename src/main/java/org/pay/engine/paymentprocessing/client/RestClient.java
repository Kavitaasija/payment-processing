package org.pay.engine.paymentprocessing.client;

import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

/**
 * Generic REST client interface for making HTTP requests to external services.
 *
 * @param <T> The request type
 * @param <R> The response type
 */
public interface RestClient<T, R> {

  /**
   * Sends a request to the specified URL with the given HTTP method.
   *
   * @param url          The URL to send the request to
   * @param method       The HTTP method to use
   * @param requestBody  The request body
   * @param responseType The class of the expected response
   * @param headers      Additional HTTP headers to include in the request
   * @param queryParams  Query parameters to include in the request
   * @return A Mono emitting the response
   */
  Mono<R> exchange(
      String url,
      HttpMethod method,
      T requestBody,
      Class<R> responseType,
      HttpHeaders headers,
      Map<String, String> queryParams);

  /**
   * Sends a GET request to the specified URL.
   *
   * @param url          The URL to send the request to
   * @param responseType The class of the expected response
   * @param headers      Additional HTTP headers to include in the request
   * @param queryParams  Query parameters to include in the request
   * @return A Mono emitting the response
   */
  default Mono<R> get(
      String url,
      Class<R> responseType,
      HttpHeaders headers,
      Map<String, String> queryParams) {
    return exchange(url, HttpMethod.GET, null, responseType, headers, queryParams);
  }

  /**
   * Sends a POST request to the specified URL.
   *
   * @param url          The URL to send the request to
   * @param requestBody  The request body
   * @param responseType The class of the expected response
   * @param headers      Additional HTTP headers to include in the request
   * @return A Mono emitting the response
   */
  default Mono<R> post(
      String url,
      T requestBody,
      Class<R> responseType,
      HttpHeaders headers) {
    return exchange(url, HttpMethod.POST, requestBody, responseType, headers, null);
  }

  /**
   * Sends a PUT request to the specified URL.
   *
   * @param url          The URL to send the request to
   * @param requestBody  The request body
   * @param responseType The class of the expected response
   * @param headers      Additional HTTP headers to include in the request
   * @return A Mono emitting the response
   */
  default Mono<R> put(
      String url,
      T requestBody,
      Class<R> responseType,
      HttpHeaders headers) {
    return exchange(url, HttpMethod.PUT, requestBody, responseType, headers, null);
  }

  /**
   * Sends a DELETE request to the specified URL.
   *
   * @param url          The URL to send the request to
   * @param responseType The class of the expected response
   * @param headers      Additional HTTP headers to include in the request
   * @return A Mono emitting the response
   */
  default Mono<R> delete(
      String url,
      Class<R> responseType,
      HttpHeaders headers) {
    return exchange(url, HttpMethod.DELETE, null, responseType, headers, null);
  }
}