package com.cse110w240t1.systemmoniter;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by fanfan on 2/7/16.
 */
public class RAMFragment extends ListFragment {
    public static String _TOTAL_MEMORY;
    public static String _AVAILABLE_MEMORY;

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
        String[] information = {"Total Memory", "Available Memory"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        _TOTAL_MEMORY = "" + memoryInfo.totalMem / 1024 / 1024+ " Mb";
        _AVAILABLE_MEMORY = "" + memoryInfo.availMem / 1024 / 1024 + " Mb";

        return rootView;
    }

}
