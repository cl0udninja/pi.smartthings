package com.cl0udninja.raspberrypi.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cl0udninja.raspberrypi.monitor.service.TemperatureService;
import com.cl0udninja.raspberrypi.monitor.web.dto.TemperatureDTO;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

  @Autowired
  private TemperatureService tempService;

  @GetMapping
  public ResponseEntity<TemperatureDTO> getTemperature() {
    TemperatureDTO temp = tempService.getTemperature();
    if (temp == null) {
      ResponseEntity.notFound();
    }
    return ResponseEntity.ok(temp);
  }
}
