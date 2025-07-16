package com.example.central;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MonitoringService {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/sensor-data", new SensorDataHandler());
        server.start();
        System.out.println("Monitoring service started on port 8080");
    }
}
