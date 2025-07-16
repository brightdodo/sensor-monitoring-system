# Warehouse & Central Monitoring System

This project consists of two decoupled Java services that demonstrate a reactive monitoring system using UDP and HTTP. It is designed using SOLID principles and follows clean architecture practices.

---

## Architecture Overview

```
[Sensors via UDP]
     ↓
[Warehouse Service: UDP Receivers]
     ↓
[Central Monitoring Service: HTTP Listener]
     ↓
[Threshold Evaluation + Logging]
```

---

## 📦 Components

### 1. Warehouse Service

- Receives UDP messages from temperature and humidity sensors.
- Sends messages to the Central Monitoring Service over HTTP.
- Ports and sensor types are configurable via `config.properties`.

### 2. Central Monitoring Service

- Accepts HTTP POST requests containing sensor data.
- Parses sensor data and logs alerts when thresholds are breached:
    - Temperature > 35°C
    - Humidity > 50%

---

## 🧾 Sensor Message Format

Messages must follow this structure:

```
sensor_id=t1; value=36
```

---

## ⚙️ Configuration

### `warehouse-service/src/main/resources/config.properties`

```properties
sensors=temperature,humidity

sensor.temperature.port=3344
sensor.humidity.port=3355
```

---

## ▶️ Running the Services

### 1. Central Monitoring Service

```bash
cd central-monitoring-service
mvn clean install
java -cp target/central-monitoring-service-1.0-SNAPSHOT.jar com.example.central.Main
```

### 2. Warehouse Service

```bash
cd warehouse-service
mvn clean install
java -cp target/warehouse-service-1.0-SNAPSHOT.jar com.example.warehouse.Main
```

---

## 🧪 Simulate Sensor Input

Send messages via UDP using `netcat`:

```bash
echo "sensor_id=t1; value=40" | nc -u -w1 localhost 3344
echo "sensor_id=h1; value=60" | nc -u -w1 localhost 3355
```

---

## 🧪 Running Tests

Both services include JUnit and Mockito tests:

```bash
mvn clean test
```

Test coverage includes:
- UDP message handling
- HTTP forwarding (mocked)
- Threshold evaluation logic

---

## 📁 Folder Structure

```
project-root/
├── warehouse-service/
│   ├── src/main/java/com/example/warehouse/
│   ├── src/test/java/
│   └── config.properties
├── central-monitoring-service/
│   ├── src/main/java/com/example/central/
│   ├── src/test/java/
└── README.md
```

---

## 📌 Key Technologies

- Java 21
- Maven
- SLF4J for logging
- JUnit 5 + Mockito for testing

---

## 📄 License

This project is provided as-is for demonstration and educational purposes.

