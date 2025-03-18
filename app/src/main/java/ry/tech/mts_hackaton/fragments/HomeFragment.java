package ry.tech.mts_hackaton.fragments;

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

import com.google.android.material.switchmaterial.SwitchMaterial;

import ry.tech.mts_hackaton.R;

public class HomeFragment extends Fragment {
    private SwitchMaterial lampSwitch;
    private SwitchMaterial acSwitch;
    private SeekBar brightnessSeekBar;
    private TextView temperatureValue;
    private TextView humidityValue;
    private TextView acTemperature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Инициализация компонентов
        lampSwitch = view.findViewById(R.id.lampSwitch);
        acSwitch = view.findViewById(R.id.acSwitch);
        brightnessSeekBar = view.findViewById(R.id.brightnessSeekBar);
        temperatureValue = view.findViewById(R.id.temperatureValue);
        humidityValue = view.findViewById(R.id.humidityValue);
        acTemperature = view.findViewById(R.id.acTemperature);

        // Обработчики событий
        lampSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            controlDevice("lamp", isChecked);
        });

        acSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            controlDevice("ac", isChecked);
        });

        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    setBrightness(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Запуск обновления данных
        startDataUpdates();

        return view;
    }

    private void controlDevice(String deviceId, boolean state) {
        // Здесь будет логика управления устройствами
    }

    private void setBrightness(int brightness) {
        // Здесь будет логика управления яркостью
    }

    private void startDataUpdates() {
        // Здесь будет логика обновления данных с датчиков
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSensorData();
                handler.postDelayed(this, 5000); // Обновление каждые 5 секунд
            }
        }, 0);
    }

    private void updateSensorData() {
        // Здесь будет логика получения данных с датчиков
    }
}