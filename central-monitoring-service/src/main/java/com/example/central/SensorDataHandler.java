package com.example.central;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class SensorDataHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes());
            System.out.println("Received: " + body);

            String[] parts = body.split(";");
            String sensorId = parts[0].split("=")[1].trim();
            int value = Integer.parseInt(parts[1].split("=")[1].trim());

            if (sensorId.startsWith("t") && value > 35) {
                System.out.println("🚨 Temperature threshold exceeded: " + value + "°C");
            } else if (sensorId.startsWith("h") && value > 50) {
                System.out.println("🚨 Humidity threshold exceeded: " + value + "%");
            }

            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        }
    }
}
