package com.example.central;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorParserTest {
    private final SensorParser sensorParser = new SensorParser();
    @Test
    void testParseTemperature() {
        var result = sensorParser.parse("sensor_id=t1; value=33");
        assertNotNull(result);
        assertTrue(result.isTemperature());
        assertEquals(33, result.value());
    }

    @Test
    void testParseHumidity() {
        var result = sensorParser.parse("sensor_id=h1; value=50");
        assertNotNull(result);
        assertTrue(result.isHumidity());
        assertEquals(50, result.value());
    }

    @Test
    void testParseUnknown() {
        var result = sensorParser.parse("sensor_id=l1; value=50");
        assertNotNull(result);
        assertFalse(result.isTemperature());
        assertFalse(result.isHumidity());
    }
}