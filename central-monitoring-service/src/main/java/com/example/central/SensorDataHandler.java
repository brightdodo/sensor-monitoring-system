package com.example.central;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class SensorDataHandler implements HttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(SensorDataHandler.class);
    private final SensorParser parser;
    private final ThresholdEvaluator evaluator;

    public SensorDataHandler(SensorParser parser, ThresholdEvaluator evaluator) {
        this.parser = parser;
        this.evaluator = evaluator;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            try (InputStream is = exchange.getRequestBody()) {
                String body = new String(is.readAllBytes());
                SensorReading reading = parser.parse(body);
                evaluator.evaluate(reading);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            exchange.sendResponseHeaders(200, 0);
            exchange.getResponseBody().close();
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}