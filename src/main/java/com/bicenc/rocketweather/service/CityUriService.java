package com.bicenc.rocketweather.service;

import com.bicenc.rocketweather.exception.RocketWeatherCityLoadException;
import com.bicenc.rocketweather.model.dto.CityWithUriDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CityUriService {

  public static final String SCR_MAIN_RESOURCES_CITIES_JSON = "classpath:cities.json";

  @Cacheable("cities")
  public List<CityWithUriDto> getCitiesFromFile() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(ResourceUtils.getFile(SCR_MAIN_RESOURCES_CITIES_JSON), new TypeReference<List<CityWithUriDto>>() {
      });
    } catch (IOException e) {
      throw new RocketWeatherCityLoadException("Error loading cities from file", e);
    }
  }

}
