package com.bicenc.rocketweather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Daily {

  private List<String> time;
  @JsonProperty("weather_code")
  private List<String> weatherCode;

}
