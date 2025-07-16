package com.example.warehouse.receiver;

import com.example.warehouse.handler.SensorHandler;

public class HumidityReceiver extends AbstractUdpSensorReceiver{
    public HumidityReceiver(SensorHandler sensorHandler) {
        super(3355, sensorHandler);
    }
}
