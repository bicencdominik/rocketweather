package com.bicenc.rocketweather.model.enumeration;

public enum WeatherCode {

  CLEAR_SKY(0, "Clear sky"),
  MAINLY_CLEAR_PARTLY_CLOUDY_OVERCAST(1, 2, 3, "Mainly clear, partly cloudy, and overcast"),
  FOG_AND_DEPOSITING_RIME_FOG(45, 48, "Fog and depositing rime fog"),
  DRIZZLE_LIGHT_MODERATE_DENSE(51, 53, 55, "Drizzle: Light, moderate, and dense intensity"),
  FREEZING_DRIZZLE_LIGHT_DENSE(56, 57, "Freezing Drizzle: Light and dense intensity"),
  RAIN_SLIGHT_MODERATE_HEAVY(61, 63, 65, "Rain: Slight, moderate and heavy intensity"),
  FREEZING_RAIN_LIGHT_HEAVY(66, 67, "Freezing Rain: Light and heavy intensity"),
  SNOW_FALL_SLIGHT_MODERATE_HEAVY(71, 73, 75, "Snow fall: Slight, moderate, and heavy intensity"),
  SNOW_GRAINS(77, "Snow grains"),
  RAIN_SHOWERS_SLIGHT_MODERATE_VIOLENT(80, 81, 82, "Rain showers: Slight, moderate, and violent"),
  SNOW_SHOWERS_SLIGHT_HEAVY(85, 86, "Snow showers: slight and heavy"),
  THUNDERSTORM_SLIGHT_MODERATE(95, "Thunderstorm: Slight or moderate"),
  THUNDERSTORM_WITH_HAIL_SLIGHT_HEAVY(96, 99, "Thunderstorm with slight and heavy hail");

  private final int[] codes;
  private final String description;

  WeatherCode(int code, String description) {
    this.codes = new int[]{code};
    this.description = description;
  }

  WeatherCode(int code1, int code2, String description) {
    this.codes = new int[]{code1, code2};
    this.description = description;
  }

  WeatherCode(int code1, int code2, int code3, String description) {
    this.codes = new int[]{code1, code2, code3};
    this.description = description;
  }

  public static WeatherCode fromCode(int code) {
    for (WeatherCode weatherCode : values()) {
      for (int c : weatherCode.getCodes()) {
        if (c == code) {
          return weatherCode;
        }
      }
    }
    throw new IllegalArgumentException("Unknown weather code: " + code);
  }

  public int[] getCodes() {
    return codes;
  }

  public String getDescription() {
    return description;
  }

}
