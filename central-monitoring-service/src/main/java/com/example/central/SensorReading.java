package com.example.central;

public record SensorReading(String sensorId, int value) {

    public boolean isTemperature() {
        return sensorId.startsWith("t");
    }

    public boolean isHumidity() {
        return sensorId.startsWith("h");
    }
}
