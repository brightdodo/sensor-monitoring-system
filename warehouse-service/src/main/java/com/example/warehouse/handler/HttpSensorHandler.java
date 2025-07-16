package com.example.warehouse.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSensorHandler implements SensorHandler {
    @Override
    public void handle(String message) {
        System.out.println("Temp Handler received: " + message);
        forwardToMonitoringService(message);
    }

    private void forwardToMonitoringService(String message) {
        try {
            URL url = new URL("http://localhost:8080/api/sensor-data");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/plain");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(message.getBytes());
            }
            conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
