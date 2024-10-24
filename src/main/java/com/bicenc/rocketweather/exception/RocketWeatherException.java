package com.bicenc.rocketweather.exception;

public class RocketWeatherException extends RuntimeException {

  public RocketWeatherException(String message) {
    super(message);
  }

  public RocketWeatherException(String message, Throwable cause) {
    super(message, cause);
  }

}
