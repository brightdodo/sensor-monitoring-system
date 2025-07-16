package com.example.warehouse.receiver;

import com.example.warehouse.handler.SensorHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class AbstractUdpSensorReceiver implements SensorReceiver {
    private final int port;
    private final SensorHandler handler;

    protected AbstractUdpSensorReceiver(int port, SensorHandler handler) {
        this.port = port;
        this.handler = handler;
    }

    @Override
    public void start() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(port)) {
                byte[] buffer = new byte[1024];
                System.out.println("Listening on port " + port);
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength());
                    handler.handle(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
