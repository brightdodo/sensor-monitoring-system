package com.example.warehouse;

import com.example.warehouse.handler.SensorHandlerFactory;
import com.example.warehouse.receiver.HumidityReceiver;
import com.example.warehouse.receiver.SensorReceiver;
import com.example.warehouse.receiver.TemperatureReceiver;

import java.util.List;

public class WarehouseApplication {
    public static void main(String[] args) {
        List<SensorReceiver> receivers = List.of(
                new TemperatureReceiver(SensorHandlerFactory.getHandler("http")),
                new HumidityReceiver(SensorHandlerFactory.getHandler("mq"))
        );

        receivers.forEach(SensorReceiver::start);
    }
}
