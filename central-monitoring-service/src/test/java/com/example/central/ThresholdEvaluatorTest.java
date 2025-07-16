package com.example.central;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ThresholdEvaluatorTest {
    private final ThresholdEvaluator evaluator = new ThresholdEvaluator();

    @ParameterizedTest
    @CsvSource({"t1,34", "t1,36", "h1,49", "h1, 51"})
    void testEvaluateThreshold(String sensorId, int value) {
        var sensorReading = new SensorReading(sensorId, value);
        assertDoesNotThrow(() -> evaluator.evaluate(sensorReading));
    }
}