package com.cse110w240t1.systemmoniter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fanfan on 2/7/16.
 */

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] information) {
        super(context, R.layout.custom_row, information);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater customInflater = LayoutInflater.from(getContext());
        View customView = customInflater.inflate(R.layout.custom_row, parent, false);

        String cellName = getItem(position);
        TextView title = (TextView) customView.findViewById(R.id.info_title);
        final TextView content = (TextView) customView.findViewById(R.id.info_content);
        ImageView image = (ImageView) customView.findViewById(R.id.info_icon);

        title.setText(cellName);
        image.setImageResource(R.drawable.default_icon);
        content.setText("Loading...");

        if (cellName == "OS Version") {
            content.setText(SystemInfoFragment._OS_VERSION);
            image.setImageResource(R.drawable.os_version_icon);
        }
        if (cellName == "Security Patch") {
            content.setText(SystemInfoFragment._SECURITY_PATCH);
            image.setImageResource(R.drawable.security_patch_icon);
        }
        if (cellName == "Phone Model") {
            content.setText(SystemInfoFragment._PHONE_MODEL);
            image.setImageResource(R.drawable.phone_model_icon);
        }
        if (cellName == "Manufacturer") {
            content.setText(SystemInfoFragment._MANUFACTURER);
            image.setImageResource(R.drawable.default_icon);
        }
        if (cellName == "SIM Card Status") {
            content.setText(SystemInfoFragment._SIM_CARD);
            image.setImageResource(R.drawable.sim_card_status);
        }
        if (cellName == "Serial Number") {
            content.setText(SystemInfoFragment._SERIAL_NUMBER);
            image.setImageResource(R.drawable.serial_number_icon);
        }
        if (cellName == "IMEI") {
            content.setText(SystemInfoFragment._IMEI);
            image.setImageResource(R.drawable.imei_icon);
        }

        if (cellName == "Percentage") {
            content.setText(BatteryFragment._PERCENTAGE);
            image.setImageResource(R.drawable.percentage_icon);
        }
        if (cellName == "Voltage") {
            content.setText(BatteryFragment._VOLTAGE);
            image.setImageResource(R.drawable.voltage_icon);
        }
        if (cellName == "Battery Temperature") {
            content.setText(BatteryFragment._TEMPERATURE);
            image.setImageResource(R.drawable.default_icon);
        }
        if (cellName == "Technology") {
            content.setText(BatteryFragment._TECHNOLOGY);
            image.setImageResource(R.drawable.default_icon);
        }

        if (cellName == "Total Memory") {
            content.setText(RAMFragment._TOTAL_MEMORY);
            image.setImageResource(R.drawable.total_memory_icon);
        }
        if (cellName == "Available Memory") {
            content.setText(RAMFragment._AVAILABLE_MEMORY);
            image.setImageResource(R.drawable.available_memory_icon);
        }

        if (cellName == "CPU Architecture") {
            content.setText(CPUFragment._CPU_ARCHITECTURE);
            image.setImageResource(R.drawable.default_icon);
        }

        if (cellName == "CPU Load") {
            final Handler CPUUpdater = new Handler();
            final int delay = 750;

            CPUUpdater.postDelayed(new Runnable() {
                @Override
                public void run() {
                    content.setText(CPUFragment._CPU_USAGE);

                    CPUUpdater.postDelayed(this, delay);
                }
            }, delay);

            image.setImageResource(R.drawable.default_icon);
        }

        if (cellName == "GPU Load") {
            final Handler GPUUpdater = new Handler();
            final int delay = 750;

            GPUUpdater.postDelayed(new Runnable() {
                @Override
                public void run() {
                    content.setText(GPUFragment._GPU_LIVE_USAGE);

                    GPUUpdater.postDelayed(this, delay);
                }
            }, delay);

            image.setImageResource(R.drawable.default_icon);
        }
        
        return customView;
    }
}
