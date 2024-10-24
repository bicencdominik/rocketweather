package com.bicenc.rocketweather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenMeteoDtoOut {

  @JsonProperty("current_units")
  private CurrentUnits currentUnits;
  private Current current;
  private DailyUnits dailyUnits;
  private Daily daily;

}
