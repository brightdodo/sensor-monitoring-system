package com.example.warehouse.receiver;

import com.example.warehouse.handler.SensorHandler;

public class GenericUdpSensorReceiver extends AbstractUdpSensorReceiver {
    public GenericUdpSensorReceiver(int port, SensorHandler handler) {
        super(port, handler);
    }
}
