package com.cse110w240t1.systemmoniter;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * Created by fanfan on 2/7/16.
 */
public class CPUFragment extends ListFragment {

    public static String _CPU_ARCHITECTURE;
    public static String _CPU_USAGE;

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
        View rootView = inflater.inflate(R.layout.cpu, container, false);
        String[] information = {"CPU Architecture", "CPU Make and Model", "Clock Speed", "Temperature", "CPU Load"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        if (Build.VERSION.SDK_INT >= 21)
            _CPU_ARCHITECTURE = Build.SUPPORTED_ABIS[0];
        else
            _CPU_ARCHITECTURE = "Unavailable at the moment";
        /*
        _CPU_USAGE = getCpuUsage() + " %";

        final Handler CPUUpdater = new Handler();
        final int delay = 750;

        CPUUpdater.postDelayed(new Runnable() {
            @Override
            public void run() {
                _CPU_USAGE = getCpuUsage() + " %";

                CPUUpdater.postDelayed(this, delay);
            }
        }, delay);
        */
        return rootView;
    }

    private int getCpuUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");

            //get first measurement for cpu usage statistics
            String cpuLoad = reader.readLine();

            String[] fields = cpuLoad.split("\\s+");  // Split on one or more spaces

            long idle1 = Long.parseLong(fields[4]);
            long cpuMeasure1 = Long.parseLong(fields[1]) + Long.parseLong(fields[2]) +
                    Long.parseLong(fields[3]) + Long.parseLong(fields[5]) +
                    Long.parseLong(fields[6]) + Long.parseLong(fields[7]) +
                    Long.parseLong(fields[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            //get second measurement for cpu usage statistics
            reader.seek(0);
            cpuLoad = reader.readLine();
            reader.close();

            fields = cpuLoad.split("\\s+");

            long idle2 = Long.parseLong(fields[4]);
            long cpuMeasure2 = Long.parseLong(fields[1]) + Long.parseLong(fields[2]) +
                                Long.parseLong(fields[3]) + Long.parseLong(fields[5]) +
                                Long.parseLong(fields[6]) + Long.parseLong(fields[7]) +
                                Long.parseLong(fields[8]);

            //calculate cpu usage
            double cpuUsage =  (100 * ( (double)(cpuMeasure2 - cpuMeasure1) /
                                ((cpuMeasure2 + idle2) - (cpuMeasure1 + idle1)) ));

            return (int)cpuUsage;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
