package com.bicenc.rocketweather.controller;

import com.bicenc.rocketweather.model.dto.GetWeatherDtoOut;
import com.bicenc.rocketweather.service.RocketWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketWeather")
@RequiredArgsConstructor
public class RocketWeatherController {

  private final RocketWeatherService rocketWeatherService;

  @GetMapping
  public GetWeatherDtoOut getWeatherForCity(@RequestBody String cityName) {
    return rocketWeatherService.getWeatherForCity(cityName);
  }

}
