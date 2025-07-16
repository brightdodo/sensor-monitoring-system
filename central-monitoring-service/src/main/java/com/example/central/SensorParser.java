package com.example.central;

public class SensorParser {
    public SensorReading parse(String body) {
        String[] parts = body.split(";");
        String sensorId = parts[0].split("=")[1].trim();
        int value = Integer.parseInt(parts[1].split("=")[1].trim());
        return new SensorReading(sensorId, value);
    }
}
