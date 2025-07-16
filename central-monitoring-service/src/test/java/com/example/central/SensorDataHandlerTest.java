package com.example.central;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SensorDataHandlerTest {
    SensorDataHandler sensorDataHandler;

    @BeforeEach
    void setUp() {
        sensorDataHandler = new SensorDataHandler(new SensorParser(), new ThresholdEvaluator());
    }

    @Test
    void handle() throws IOException {
        // Arrange
        String requestBody = "sensor_id=t1; value=40";

        HttpExchange exchange = mock(HttpExchange.class);

        // Mock input stream
        when(exchange.getRequestMethod()).thenReturn("POST");
        when(exchange.getRequestBody()).thenReturn(new ByteArrayInputStream(requestBody.getBytes()));

        // Mock output stream
        OutputStream responseBody = new ByteArrayOutputStream();
        when(exchange.getResponseBody()).thenReturn(responseBody);

        // Act
        sensorDataHandler.handle(exchange);

        // Assert (optional: check that sendResponseHeaders was called)
        verify(exchange).sendResponseHeaders(eq(200), eq(0L));
        verify(exchange).getResponseBody();
    }
}