package com.example.warehouse;

import com.example.warehouse.config.ConfigLoader;
import com.example.warehouse.handler.SensorHandlerFactory;
import com.example.warehouse.receiver.GenericUdpSensorReceiver;
import com.example.warehouse.receiver.SensorReceiver;

import java.util.ArrayList;
import java.util.List;

public class WarehouseApplication {
    public static void main(String[] args) {
        List<SensorReceiver> receivers = new ArrayList<>();

        for (String sensorType : ConfigLoader.getSensorTypes()) {
            int port = ConfigLoader.getSensorPort(sensorType);
            String handlerKey = ConfigLoader.getSensorHandler(sensorType);

            receivers.add(new GenericUdpSensorReceiver(port, SensorHandlerFactory.getHandler(handlerKey)));
        }

        receivers.forEach(SensorReceiver::start);
    }
}
