package com.bicenc.rocketweather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityWithUriDto {

  private String cityName;
  private String uri;

}
