package com.cse110w240t1.systemmoniter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


/**
 * Created by fanfan on 2/7/16.
 */
public class GPUFragment extends ListFragment {
    public static String _GPU_LIVE_USAGE;

    public GPUFragment() {
    }

    public static GPUFragment newInstance(int sectionNumber) {
        GPUFragment fragment = new GPUFragment();
        Bundle args = new Bundle();
        args.putInt("GPU_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cpu, container, false);
        String[] information = {"Architecture", "Make and Model", "Clock Speed", "Temperature", "GPU Load"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        final Handler GPUUpdater = new Handler();
        final int delay = 750;

        GPUUpdater.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream fis = new FileInputStream("/sys/devices/platform/gpusysfs/gpu_busy");
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    final BufferedReader br = new BufferedReader(isr);
                    _GPU_LIVE_USAGE = br.readLine() + "%";
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GPUUpdater.postDelayed(this, delay);
            }
        }, delay);

        return rootView;
    }
}
