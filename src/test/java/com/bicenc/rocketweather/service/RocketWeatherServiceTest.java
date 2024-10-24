package com.bicenc.rocketweather.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bicenc.rocketweather.exception.RocketWeatherCityNotFoundException;
import com.bicenc.rocketweather.model.dto.CityWithUriDto;
import com.bicenc.rocketweather.model.dto.GetWeatherDtoOut;
import com.bicenc.rocketweather.model.dto.OpenMeteoDtoOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RocketWeatherServiceTest {

  @Mock
  private CityUriService cityUriService;

  @Mock
  private HttpService httpService;

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private RocketWeatherService rocketWeatherService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getWeatherForCity_ReturnsWeatherData_ForValidCity() throws JsonProcessingException {
    String cityName = "Prague";
    String uri = "http://example.com/weather";
    String response = "{\n  \"current\": {\n    \"time\": \"2024-10-24T18:30\",\n    \"interval\": 900,\n    \"temperature_2m\": 15.2,\n    \"relative_humidity_2m\": 62.0,\n    \"weather_code\": 1.0,\n    \"wind_speed_10m\": 4.8\n  },\n  \"dailyUnits\": null,\n  \"daily\": {\n    \"time\": [\n      \"2024-10-24\",\n      \"2024-10-25\",\n      \"2024-10-26\",\n      \"2024-10-27\",\n      \"2024-10-28\",\n      \"2024-10-29\",\n      \"2024-10-30\"\n    ],\n    \"weather_code\": [\n      \"3\",\n      \"3\",\n      \"45\",\n      \"3\",\n      \"45\",\n      \"45\",\n      \"45\"\n    ]\n  },\n  \"current_units\": {\n    \"time\": \"iso8601\",\n    \"interval\": \"seconds\",\n    \"temperature_2m\": \"°C\",\n    \"relative_humidity_2m\": \"%%\",\n    \"weather_code\": \"wmo code\",\n    \"wind_speed_10m\": \"km/h\"\n  }\n}".formatted();
    HttpResponse<String> httpResponse = mock(HttpResponse.class);
    OpenMeteoDtoOut openMeteoDtoOut = new OpenMeteoDtoOut();

    when(cityUriService.getCitiesFromFile()).thenReturn(List.of(new CityWithUriDto(cityName, uri)));
    when(httpService.callApi(uri)).thenReturn(httpResponse);
    when(httpResponse.body()).thenReturn(response);
    when(objectMapper.readValue(response, OpenMeteoDtoOut.class)).thenReturn(openMeteoDtoOut);

    GetWeatherDtoOut result = rocketWeatherService.getWeatherForCity(cityName);

    assertNotNull(result);
    assertEquals(cityName, result.getCityName());
  }

  @Test
  void getWeatherForCity_ThrowsException_ForInvalidCity() {
    String cityName = "InvalidCity";

    when(cityUriService.getCitiesFromFile()).thenReturn(List.of(new CityWithUriDto("Prague", "http://example.com/weather")));

    assertThrows(RocketWeatherCityNotFoundException.class, () -> rocketWeatherService.getWeatherForCity(cityName));
  }

  @Test
  void getWeatherForCity_ThrowsRuntimeException_ForJsonProcessingException() throws JsonProcessingException {
    String cityName = "Prague";
    String uri = "http://example.com/weather";
    String response = "{\"current\": {\"currentTemperature\": 15.8, \"relativeHumidity\": 60, \"windSpeed\": 6.1}, \"currentUnits\": {\"current\": \"°C\", \"relativeHumidity\": \"%\", \"windSpeed\": \"km/h\"}, \"daily\": {\"time\": [\"2024-10-24\"], \"weatherCode\": [3]}}";
    HttpResponse<String> httpResponse = mock(HttpResponse.class);

    when(cityUriService.getCitiesFromFile()).thenReturn(List.of(new CityWithUriDto(cityName, uri)));
    when(httpService.callApi(uri)).thenReturn(httpResponse);
    when(httpResponse.body()).thenReturn(response);
    when(objectMapper.readValue(response, OpenMeteoDtoOut.class)).thenThrow(JsonProcessingException.class);

    assertThrows(RuntimeException.class, () -> rocketWeatherService.getWeatherForCity(cityName));
  }

}