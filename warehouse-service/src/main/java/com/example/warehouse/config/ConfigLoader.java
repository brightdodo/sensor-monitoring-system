package com.example.warehouse.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("bootstrap.properties")) {
            if (input == null) throw new IllegalStateException("config.properties not found!");
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load config", ex);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static List<String> getSensorTypes() {
        String sensors = properties.getProperty("sensors", "");
        return Arrays.asList(sensors.split(","));
    }

    public static int getSensorPort(String sensorType) {
        return Integer.parseInt(properties.getProperty("sensor." + sensorType + ".port"));
    }

    public static String getSensorHandler(String sensorType) {
        return properties.getProperty("sensor." + sensorType + ".handler");
    }
}
