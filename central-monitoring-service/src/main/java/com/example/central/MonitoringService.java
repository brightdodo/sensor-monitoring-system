package com.example.central;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MonitoringService {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        SensorParser parser = new SensorParser();
        ThresholdEvaluator evaluator = new ThresholdEvaluator();
        server.createContext("/api/sensor-data", new SensorDataHandler(parser, evaluator));
        server.start();
        logger.info("Server started on port 8080");
    }
}
