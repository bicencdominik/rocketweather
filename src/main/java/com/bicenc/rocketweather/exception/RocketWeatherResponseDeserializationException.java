package com.bicenc.rocketweather.exception;

public class RocketWeatherResponseDeserializationException extends RocketWeatherException {

  public RocketWeatherResponseDeserializationException(String message) {
    super(message);
  }

  public RocketWeatherResponseDeserializationException(String message, Throwable cause) {
    super(message, cause);
  }
}
