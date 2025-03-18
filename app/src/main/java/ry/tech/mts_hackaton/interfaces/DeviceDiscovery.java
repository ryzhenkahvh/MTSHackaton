package ry.tech.mts_hackaton.interfaces;

import java.util.List;

import ry.tech.mts_hackaton.models.Device;

public interface DeviceDiscovery {
    List<Device> scanForDevices();
    boolean connectDevice(Device device);
    void disconnectDevice(Device device);
}