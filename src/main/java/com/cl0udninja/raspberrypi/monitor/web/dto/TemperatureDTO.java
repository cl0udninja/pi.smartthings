package com.cl0udninja.raspberrypi.monitor.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TemperatureDTO {

  @JsonProperty
  double celsius;
  @JsonProperty
  double fahrenheit;

}
