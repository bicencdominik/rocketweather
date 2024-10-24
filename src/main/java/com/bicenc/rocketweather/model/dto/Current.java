package com.bicenc.rocketweather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {

  private String time;
  private int interval;
  @JsonProperty("temperature_2m")
  private float currentTemperature;
  @JsonProperty("relative_humidity_2m")
  private float relativeHumidity;
  @JsonProperty("weather_code")
  private int weatherCode;
  @JsonProperty("wind_speed_10m")
  private float windSpeed;
}
