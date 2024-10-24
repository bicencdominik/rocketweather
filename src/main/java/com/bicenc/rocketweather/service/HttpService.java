package com.bicenc.rocketweather.service;

import com.bicenc.rocketweather.exception.RocketWeatherCallApiException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HttpService {

  private final HttpClient client;

  @Cacheable("weatherData")
  public HttpResponse<String> callApi(String uri) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .timeout(Duration.ofSeconds(10))
        .GET()
        .build();
    try {
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RocketWeatherCallApiException(STR."Error during call \{uri}", e);
    }
  }
}
