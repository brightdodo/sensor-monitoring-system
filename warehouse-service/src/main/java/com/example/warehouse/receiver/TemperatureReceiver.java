package com.example.warehouse.receiver;

import com.example.warehouse.handler.SensorHandler;

public class TemperatureReceiver extends AbstractUdpSensorReceiver{
    public TemperatureReceiver(SensorHandler handler) {
        super(3344, handler);
    }
}
