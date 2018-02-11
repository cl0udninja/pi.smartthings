package com.cl0udninja.raspberrypi.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cl0udninja.raspberrypi.monitor.web.dto.TemperatureDTO;
import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemperatureService {

  /**
   * @return current temperature in celsius
   */
  public TemperatureDTO getTemperature() {
    W1Master master = new W1Master();
    List<TemperatureSensor> w1Devices = master.getDevices(TemperatureSensor.class);
    log.debug(String.format("Found devices %s {%s}", w1Devices.size(), w1Devices));

    if (w1Devices == null || w1Devices.isEmpty()) {
      return null;
    }

    final TemperatureDTO tempDTO = new TemperatureDTO();

    w1Devices.stream().forEach(device -> {
      log.debug(String.format("Device name                 : %s", device.getName()));
      log.debug(String.format("Device properties           : %s", device.getProperties()));
      log.debug(String.format("Device temperature          : %s ˚C", device.getTemperature(TemperatureScale.CELSIUS)));
      log.debug(
          String.format("Device temperature          : %s ˚F", device.getTemperature(TemperatureScale.FARENHEIT)));
      log.debug(String.format("Device scale                : %s", device.getScale()));
      log.debug(String.format("Device tag                  : %s", device.getTag()));
      tempDTO.setCelsius(device.getTemperature(TemperatureScale.CELSIUS));
      tempDTO.setFahrenheit(device.getTemperature(TemperatureScale.FARENHEIT));
    });
    return tempDTO;
  }

}
