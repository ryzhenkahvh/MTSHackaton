package ry.tech.mts_hackaton.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

import ry.tech.mts_hackaton.IoTService;
import ry.tech.mts_hackaton.R;
import ry.tech.mts_hackaton.adapters.DeviceAdapter;
import ry.tech.mts_hackaton.controllers.IoTDeviceController;
import ry.tech.mts_hackaton.interfaces.DeviceClickListener;
import ry.tech.mts_hackaton.models.Device;

public class HomeFragment extends Fragment {
    private IoTDeviceController deviceController;
    private List<Device> devices;
    private DeviceAdapter deviceAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceController = IoTDeviceController.getInstance();
        devices = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView devicesRecyclerView = view.findViewById(R.id.devicesRecyclerView);
        deviceAdapter = new DeviceAdapter(devices, new DeviceClickListener() {
            @Override
            public void onDeviceStateChanged(Device device, boolean isOn) {
                if (isOn) {
                    deviceController.turnOn(device.getId());
                } else {
                    deviceController.turnOff(device.getId());
                }
            }

            @Override
            public void onDeviceSettingsClick(Device device) {
                showDeviceSettings(device);
            }
        });

        devicesRecyclerView.setAdapter(deviceAdapter);
        devicesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Запуск сервиса
        Intent serviceIntent = new Intent(getContext(), IoTService.class);
        requireContext().startService(serviceIntent);

        return view;
    }

    private void showDeviceSettings(Device device) {
        DeviceSettingsDialog dialog = DeviceSettingsDialog.newInstance(device);
        dialog.show(getChildFragmentManager(), "device_settings");
    }

    private void updateDevicesList() {
        devices.clear();
        devices.addAll(deviceController.getAllDevices());
        deviceAdapter.notifyDataSetChanged();
    }
}
