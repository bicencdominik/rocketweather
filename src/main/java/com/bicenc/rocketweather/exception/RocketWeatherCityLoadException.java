package com.bicenc.rocketweather.exception;

public class RocketWeatherCityLoadException extends RocketWeatherException {

  public RocketWeatherCityLoadException(String message) {
    super(message);
  }

  public RocketWeatherCityLoadException(String message, Throwable cause) {
    super(message, cause);
  }
}
