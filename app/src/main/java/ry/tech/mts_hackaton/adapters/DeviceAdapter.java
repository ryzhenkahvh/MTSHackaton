package ry.tech.mts_hackaton.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

import ry.tech.mts_hackaton.R;
import ry.tech.mts_hackaton.interfaces.DeviceClickListener;
import ry.tech.mts_hackaton.models.Device;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    private List<Device> devices;
    private DeviceClickListener listener;

    public DeviceAdapter(List<Device> devices, DeviceClickListener listener) {
        this.devices = devices;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Device device = devices.get(position);
        holder.bind(device);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        private TextView deviceName;
        private SwitchMaterial deviceSwitch;
        private ImageView deviceIcon;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.deviceName);
            deviceSwitch = itemView.findViewById(R.id.deviceSwitch);
            deviceIcon = itemView.findViewById(R.id.deviceIcon);

            deviceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeviceStateChanged(devices.get(position), isChecked);
                }
            });

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeviceSettingsClick(devices.get(position));
                }
            });
        }

        public void bind(Device device) {
            deviceName.setText(device.getName());
            deviceSwitch.setChecked(device.isOn());
            // Установка иконки в зависимости от типа устройства
        }
    }
}