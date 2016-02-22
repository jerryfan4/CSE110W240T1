package com.cse110w240t1.systemmoniter;

import java.lang.Object;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanfan on 2/7/16.
 */
public class RAMFragment extends ListFragment {
    public static String _RAM_CAPACITY;

    public RAMFragment() {
    }

    public static RAMFragment newInstance(int sectionNumber) {
        RAMFragment fragment = new RAMFragment();
        Bundle args = new Bundle();
        args.putInt("RAM_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ram, container, false);
        String[] information = {"Make and Model", "Speed", "RAM Capacity", "Live Usage"};
        long capsize = getTotalMemory();
        _RAM_CAPACITY = Long.toString(capsize) + "MB";
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }

    public long getTotalMemory(){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1048576L;
        return availableMegs;
    }
}
