package com.bicenc.rocketweather.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND)
public class RocketWeatherCityNotFoundException extends RocketWeatherException {

  public RocketWeatherCityNotFoundException(String message) {
    super(message);
  }

  public RocketWeatherCityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
