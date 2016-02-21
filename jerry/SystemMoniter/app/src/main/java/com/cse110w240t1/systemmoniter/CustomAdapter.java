package com.cse110w240t1.systemmoniter;

import android.content.Context;
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
        TextView content = (TextView) customView.findViewById(R.id.info_content);
        ImageView image = (ImageView) customView.findViewById(R.id.info_icon);

        title.setText(cellName);
        image.setImageResource(R.drawable.ram_icon);
        content.setText("Hello World");

        if (cellName == "OS Version") {
            content.setText(SystemInfoFragment._OS_VERSION);
            image.setImageResource(R.drawable.default_icon);
        }
        if (cellName == "Security Patch") {
            content.setText(SystemInfoFragment._SECURITY_PATCH);
            image.setImageResource(R.drawable.ram_icon);
        }
        if (cellName == "Phone Model") {
            content.setText(SystemInfoFragment._PHONE_MODEL);
            image.setImageResource(R.drawable.battery_icon);
        }
        if (cellName == "Manufacturer") {
            content.setText(SystemInfoFragment._MANUFACTURER);
            image.setImageResource(R.drawable.gpu_icon);
        }
        if (cellName == "SIM Card Status") {
            content.setText(SystemInfoFragment._SIM_CARD);
            image.setImageResource(R.drawable.os_icon);
        }
        if (cellName == "Serial Number") {
            content.setText(SystemInfoFragment._SERIAL_NUMBER);
            image.setImageResource(R.drawable.default_icon);
        }
        if (cellName == "IMEI") {
            content.setText(SystemInfoFragment._IMEI);
            image.setImageResource(R.drawable.ram_icon);
        }

        if (cellName == "Percentage") {
            content.setText(BatteryFragment._PERCENTAGE);
            image.setImageResource(R.drawable.battery_icon);
        }
        if (cellName == "Voltage") {
            content.setText(BatteryFragment._VOLTAGE);
            image.setImageResource(R.drawable.default_icon);
        }
        if (cellName == "Battery Temperature") {
            content.setText(BatteryFragment._TEMPERATURE);
            image.setImageResource(R.drawable.os_icon);
        }
        if (cellName == "Technology") {
            content.setText(BatteryFragment._TECHNOLOGY);
            image.setImageResource(R.drawable.gpu_icon);
        }
        return customView;
    }
}
