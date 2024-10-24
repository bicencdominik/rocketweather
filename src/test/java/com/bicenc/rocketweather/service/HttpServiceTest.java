package com.bicenc.rocketweather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bicenc.rocketweather.exception.RocketWeatherCallApiException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HttpServiceTest {

  @Mock
  private HttpClient client;

  @InjectMocks
  private HttpService httpService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void callApi_ReturnsHttpResponse_ForValidUri() throws IOException, InterruptedException {
    String uri = "http://example.com/weather";
    HttpResponse<String> httpResponse = mock(HttpResponse.class);

    when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

    HttpResponse<String> result = httpService.callApi(uri);

    assertNotNull(result);
    verify(client, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
  }

  @Test
  void callApi_ThrowsRocketWeatherCallApiException_ForIOException() throws IOException, InterruptedException {
    String uri = "http://example.com/weather";

    when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(IOException.class);

    assertThrows(RocketWeatherCallApiException.class, () -> httpService.callApi(uri));
  }

  @Test
  void callApi_ThrowsRocketWeatherCallApiException_ForInterruptedException() throws IOException, InterruptedException {
    String uri = "http://example.com/weather";

    when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(InterruptedException.class);

    assertThrows(RocketWeatherCallApiException.class, () -> httpService.callApi(uri));
  }
}