package com.example.warehouse.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpSensorHandler implements SensorHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpSensorHandler.class);

    @Override
    public void handle(String message) {
        logger.info("Http Handler received: {}", message);
        forwardToMonitoringService(message);
    }

    private void forwardToMonitoringService(String message) {
        try {
            URL url = URI.create("http://localhost:8080/api/sensor-data").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/plain");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(message.getBytes());
            }
            conn.getResponseCode();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
