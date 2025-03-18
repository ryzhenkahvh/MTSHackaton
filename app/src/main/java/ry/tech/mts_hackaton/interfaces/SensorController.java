package ry.tech.mts_hackaton.interfaces;

import java.util.List;

import ry.tech.mts_hackaton.models.SensorData;

public interface SensorController {
    SensorData getSensorData(String sensorId);
    List<SensorData> getSensorHistory(String sensorId, long startTime, long endTime);
    void subscribeSensorUpdates(String sensorId, SensorUpdateCallback callback);
}