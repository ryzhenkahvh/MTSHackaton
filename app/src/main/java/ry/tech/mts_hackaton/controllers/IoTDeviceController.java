package ry.tech.mts_hackaton.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ry.tech.mts_hackaton.interfaces.DeviceController;
import ry.tech.mts_hackaton.models.Device;

public class IoTDeviceController implements DeviceController {
    private static IoTDeviceController instance;
    private Map<String, Device> devices;
    private final DeviceCommunicator communicator;

    private IoTDeviceController() {
        devices = new HashMap<>();
        communicator = new DeviceCommunicator();
    }

    public static IoTDeviceController getInstance() {
        if (instance == null) {
            instance = new IoTDeviceController();
        }
        return instance;
    }

    @Override
    public void turnOn(String deviceId) {
        Device device = devices.get(deviceId);
        if (device != null) {
            communicator.sendCommand(device, "power", true);
            device.setOn(true);
        }
    }

    @Override
    public void turnOff(String deviceId) {
        Device device = devices.get(deviceId);
        if (device != null) {
            communicator.sendCommand(device, "power", false);
            device.setOn(false);
        }
    }

    @Override
    public void setParameter(String deviceId, String parameter, Object value) {
        Device device = devices.get(deviceId);
        if (device != null) {
            communicator.sendCommand(device, parameter, value);
        }
    }

    @Override
    public Device getDeviceState(String deviceId) {
        return devices.get(deviceId);
    }

    public List<Device> getAllDevices() {
        return new ArrayList<>(devices.values());
    }
}