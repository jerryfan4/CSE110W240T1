package com.cse110w240t1.systemmoniter;

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
public class BatteryFragment extends ListFragment {
    public static String _BATTERY_PROPERTY_CURRENT_NOW;
    public static String _BATTERY_PROPERTY_CAPACITY;


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
        String[] information = {"Capacity", "Current"};

        _BATTERY_PROPERTY_CURRENT_NOW = ""+(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
        _BATTERY_PROPERTY_CAPACITY = ""+(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }
}
