package ry.tech.mts_hackaton.interfaces;

import ry.tech.mts_hackaton.models.SensorData;

public interface SensorUpdateCallback {
    void onSensorUpdate(SensorData data);
}