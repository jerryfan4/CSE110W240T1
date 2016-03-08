package com.cse110w240t1.systemmoniter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * Created by fanfan on 2/7/16.
 */
public class GPUFragment extends ListFragment {

    //S = Samsung, Q = Qualcomm
    private static final String S_GPU_USAGE = "/sys/devices/platform/gpusysfs/gpu_busy";
    private static final String Q_GPU_USAGE = "/sys/class/kgsl/kgsl-3d0/gpubusy";
    private static final String S_GPU_CLOCK = "/sys/devices/platform/gpusysfs/gpu_max_clock";
    private static final String Q_GPU_CLOCK = "/sys/class/kgsl/kgsl-3d0/max_gpuclk";


    public static String _GPU_LIVE_USAGE;
    public static String _GPU_CLOCK_SPEED;

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
        View rootView = inflater.inflate(R.layout.gpu, container, false);
        String[] information = {"OpenGL ES Version", "GPU Make", "GPU Model", "GPU Clock Speed", "GPU Load"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);


        //get GPU Clock Speed
        File c = new File(S_GPU_CLOCK);

        if(c.exists() && !c.isDirectory()) {
            getGpuClock(c);
        }
        else {
            c = new File(Q_GPU_CLOCK);

            if(c.exists() && !c.isDirectory()) {
                getGpuClock(c);
            }
            else {
                _GPU_CLOCK_SPEED = "Unavailable";
            }
        }



        //get GPU usage
        File f = new File(S_GPU_USAGE);

        if(f.exists() && !f.isDirectory()) {
            getGpuUsage(f);
        }
        else {
            f = new File(Q_GPU_USAGE);

            if(f.exists() && !f.isDirectory()) {
                getGpuUsage(f);
            }
            else {
                _GPU_LIVE_USAGE = "Unavailable";
            }
        }

        return rootView;
    }


    //method to help get gpu clock speed
    private void getGpuClock(final File c) {
        try {
            RandomAccessFile reader = new RandomAccessFile(c, "r");
            if ( (c.getPath()).equals(Q_GPU_CLOCK) ) {
                long clock_speed = Long.parseLong(reader.readLine());
                clock_speed = (long) ((double) clock_speed / 1000000); //convert Hz to MHz
                _GPU_CLOCK_SPEED = clock_speed + " MHz";
            }
            else {
                _GPU_CLOCK_SPEED = reader.readLine() + " MHz";
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //method to help get gpu live usage
    private void getGpuUsage(final File f) {
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();

                final Handler GPUUpdater = new Handler();
                final int delay = 750;

                GPUUpdater.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            RandomAccessFile reader = new RandomAccessFile(f, "r");

                            if ( (f.getPath()).equals(Q_GPU_USAGE) ) {
                                String gpuLoad = reader.readLine();
                                String[] fields = gpuLoad.split("\\s+");  // Split on one or more spaces

                                long busy = Long.parseLong(fields[1]);
                                long total = Long.parseLong(fields[2]);
                                long usage = (int)( ((double) busy) / total * 100);

                                _GPU_LIVE_USAGE = usage + "%";
                            }
                            else {
                                _GPU_LIVE_USAGE = reader.readLine() + "%";
                            }

                            reader.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        GPUUpdater.postDelayed(this, delay);
                    }
                }, delay);

                Looper.loop();
            }
        }).start();
    }
}