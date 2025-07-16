package com.example.warehouse.receiver;

import com.example.warehouse.handler.HttpSensorHandler;
import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GenericUdpSensorReceiverTest {
    @Test
    void testUdpReceiverPublishesToRabbitMQ() throws Exception {
        int testPort = 4455;
        HttpSensorHandler sensorHandler = mock(HttpSensorHandler.class);
        GenericUdpSensorReceiver receiver = new GenericUdpSensorReceiver(testPort, sensorHandler);
        receiver.start();

        DatagramSocket clientSocket = new DatagramSocket();
        String testMessage = "sensor_id=t1; value=45";
        byte[] sendData = testMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), testPort);
        clientSocket.send(sendPacket);
        clientSocket.close();

        verify(sensorHandler, atLeastOnce()).handle(testMessage);
    }
}