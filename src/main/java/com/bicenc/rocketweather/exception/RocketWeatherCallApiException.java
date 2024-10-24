package com.bicenc.rocketweather.exception;

public class RocketWeatherCallApiException extends RocketWeatherException {

  public RocketWeatherCallApiException(String message) {
    super(message);
  }

  public RocketWeatherCallApiException(String message, Throwable cause) {
    super(message, cause);
  }

}
