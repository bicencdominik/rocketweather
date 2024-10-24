package com.bicenc.rocketweather.service;

import com.bicenc.rocketweather.exception.RocketWeatherCityNotFoundException;
import com.bicenc.rocketweather.exception.RocketWeatherResponseDeserializationException;
import com.bicenc.rocketweather.model.dto.CityWithUriDto;
import com.bicenc.rocketweather.model.dto.Daily;
import com.bicenc.rocketweather.model.dto.ForecastDtoOut;
import com.bicenc.rocketweather.model.dto.GetWeatherDtoOut;
import com.bicenc.rocketweather.model.dto.OpenMeteoDtoOut;
import com.bicenc.rocketweather.model.enumeration.WeatherCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RocketWeatherService {

  private final CityUriService cityUriService;
  private final HttpService httpService;
  private final ObjectMapper objectMapper;

  public GetWeatherDtoOut getWeatherForCity(String cityName) {
    String uri = cityUriService.getCitiesFromFile().stream()
        .filter(city -> city.getCityName().equalsIgnoreCase(cityName))
        .findFirst()
        .map(CityWithUriDto::getUri)
        .orElseThrow(() -> new RocketWeatherCityNotFoundException(STR."City \{cityName} not found"));

    try {
      String response = httpService.callApi(uri).body();
      OpenMeteoDtoOut openMeteoDtoOut = objectMapper.readValue(response, OpenMeteoDtoOut.class);
      return mapFromOpenMeteoDtoOut(openMeteoDtoOut, cityName);
    } catch (JsonProcessingException e) {
      throw new RocketWeatherResponseDeserializationException("Error during deserialization of api response", e);
    }
  }

  private GetWeatherDtoOut mapFromOpenMeteoDtoOut(OpenMeteoDtoOut openMeteoDtoOut, String cityName) {
    return new GetWeatherDtoOut(
        cityName,
        STR."\{openMeteoDtoOut.getCurrent().getCurrentTemperature()} \{openMeteoDtoOut.getCurrentUnits().getCurrent()}",
        STR."\{openMeteoDtoOut.getCurrent().getRelativeHumidity()} \{openMeteoDtoOut.getCurrentUnits().getRelativeHumidity()}",
        STR."\{openMeteoDtoOut.getCurrent().getWindSpeed()} \{openMeteoDtoOut.getCurrentUnits().getWindSpeed()}",
        mapFromDaily(openMeteoDtoOut.getDaily())
    );
  }

  private List<ForecastDtoOut> mapFromDaily(Daily daily) {
    return daily.getTime().stream()
        .map(time -> new ForecastDtoOut(time, WeatherCode.fromCode(Integer.parseInt(daily.getWeatherCode().get(daily.getTime().indexOf(time)))).getDescription()))
        .toList();
  }
}