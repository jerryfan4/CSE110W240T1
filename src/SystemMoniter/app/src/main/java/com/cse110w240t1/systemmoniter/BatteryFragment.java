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
        String[] information = {"Capacity", "Live Usage", "Voltage"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, information);
        setListAdapter(adapter);

        return rootView;
    }
}
