package com.bicenc.rocketweather.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherDtoOut {

  private String cityName;
  private String currentTemperature;
  private String currentHumidity;
  private String currentWindSpeed;
  private List<ForecastDtoOut> forecastList;

}
