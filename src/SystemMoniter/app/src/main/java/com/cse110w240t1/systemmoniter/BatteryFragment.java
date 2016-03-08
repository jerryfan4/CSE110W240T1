package com.cse110w240t1.systemmoniter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by fanfan on 2/7/16.
 */
public class BatteryFragment extends ListFragment {
    public static String _PERCENTAGE;
    public static String _TEMPERATURE;
    public static String _VOLTAGE;
    public static String _TECHNOLOGY;

    public BatteryFragment() {
    }

    public static BatteryFragment newInstance(int sectionNumber) {
        BatteryFragment fragment = new BatteryFragment();
        Bundle args = new Bundle();
        args.putInt("Battery_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.battery, container, false);
        String[] information = {"Percentage", "Voltage", "Battery Temperature", "Technology"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        //getBatteryInfo();

        return rootView;
    }

    /*private void getBatteryInfo() {

        BroadcastReceiver batteryLevel = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);

                _TEMPERATURE = temperature/10 + " °C";
                _VOLTAGE = voltage + " mV";
                _PERCENTAGE = level + "%";
                _TECHNOLOGY = technology;
            }
        };

        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(batteryLevel, batteryLevelFilter);
    }*/
}