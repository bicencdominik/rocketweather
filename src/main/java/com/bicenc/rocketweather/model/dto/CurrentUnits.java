package com.bicenc.rocketweather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentUnits {

  private String time;
  private String interval;
  @JsonProperty("temperature_2m")
  private String current;
  @JsonProperty("relative_humidity_2m")
  private String relativeHumidity;
  @JsonProperty("weather_code")
  private String weatherCode;
  @JsonProperty("wind_speed_10m")
  private String windSpeed;
}
