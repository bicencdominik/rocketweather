package com.bicenc.rocketweather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyUnits {

  private String time;
  @JsonProperty("weather_code")
  private String weatherCode;

}
