package com.example.warehouse.handler;

public class SensorHandlerFactory {
    public static SensorHandler getHandler(String protocol) {
        return switch (protocol) {
            case "http" -> new HttpSensorHandler();
            case "mq" -> new MessageBrokerHandler();
            default -> throw new IllegalArgumentException("Unknown communication protocol: " + protocol);
        };
    }
}
