package com.cse110w240t1.systemmoniter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by fanfan on 2/7/16.
 */
public class CPUFragment extends ListFragment {
    public static String _CPU_LOAD;
    public static String _CLOCK_SPEED;
    public static String _VOLTAGE;
    public static String _TECHNOLOGY;

    public CPUFragment() {
    }

    public static CPUFragment newInstance(int sectionNumber) {
        CPUFragment fragment = new CPUFragment();
        Bundle args = new Bundle();
        args.putInt("CPU_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gpu, container, false);
        String[] information = {"Architecture", "Make and Model", "Clock Speed", "Temperature", "Live Usage"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }

    private double getCpuInfo() {

        BroadcastReceiver batteryLevel = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
                double cpu_load = 0;
                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                _CPU_LOAD = ;

            }
        };

        IntentFilter batteryLevelFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        getActivity().registerReceiver(batteryLevel, batteryLevelFilter);
        return 0;
    }
}
