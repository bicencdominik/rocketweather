package com.bicenc.rocketweather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDtoOut {

  private String day;
  private String forecast;

}
