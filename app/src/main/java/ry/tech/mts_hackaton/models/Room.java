package ry.tech.mts_hackaton.models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String name;
    private List<Device> devices;
    private List<SensorData> sensorData;

    public Room(String id, String name) {
        this.id = id;
        this.name = name;
        this.devices = new ArrayList<>();
        this.sensorData = new ArrayList<>();
    }

    // get/set?
}