package ry.tech.mts_hackaton.interfaces;

import ry.tech.mts_hackaton.models.Device;

public interface DeviceClickListener {
    void onDeviceStateChanged(Device device, boolean isOn);
    void onDeviceSettingsClick(Device device);
}