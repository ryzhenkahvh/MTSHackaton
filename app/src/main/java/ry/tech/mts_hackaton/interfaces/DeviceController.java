package ry.tech.mts_hackaton.interfaces;

import ry.tech.mts_hackaton.models.Device;

public interface DeviceController {
    void turnOn(String deviceId);
    void turnOff(String deviceId);
    void setParameter(String deviceId, String parameter, Object value);
    Device getDeviceState(String deviceId);
}