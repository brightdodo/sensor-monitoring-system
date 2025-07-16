package com.example.central;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThresholdEvaluator {
    private static final Logger logger = LoggerFactory.getLogger(ThresholdEvaluator.class);

    public void evaluate(SensorReading reading) {
        if (reading.isTemperature() && reading.value() > 35) {
            logger.warn("🚨 Temperature threshold exceeded: {}°C", reading.value());
        } else if (reading.isHumidity() && reading.value() > 50) {
            logger.warn("🚨 Humidity threshold exceeded: {}%", reading.value());
        } else {
            logger.info("Sensor data within thresholds: {} = {}", reading.sensorId(), reading.value());
        }
    }
}
