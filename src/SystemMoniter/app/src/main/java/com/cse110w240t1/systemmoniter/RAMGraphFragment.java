package com.cse110w240t1.systemmoniter;

/**
 * Created by fanfan on 3/1/16.
 */
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//the hardest part for me was getting to the libraries to work; I did some dumb ass shit which
//cost me hours but essentially what I did wrong was double up on the lbraries, as a result
// I had this stupid bug thta prevent the build from working; essentially we just need to drag the
//graphview.jar file into the 'libs' folder in the 'app' folder, given in project view to begin with

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;//random is used here to generate random inputs


public class RAMGraphFragment extends Fragment {

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    private ActivityManager activityManager;
    private ActivityManager.MemoryInfo memoryInfo;

    public RAMGraphFragment() {
    }

    public static RAMGraphFragment newInstance(int sectionNumber) {
        RAMGraphFragment fragment = new RAMGraphFragment();
        Bundle args = new Bundle();
        args.putInt("RAM_Graph", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ram_graph, container, false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        series = new LineGraphSeries<DataPoint>();//data container
        graph.addSeries(series);//attach container
        Viewport viewport = graph.getViewport();//not sure
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        long Mem = (memoryInfo.totalMem - memoryInfo.availMem) / 1024 / 1024;
        viewport.setXAxisBoundsStatus(Viewport.AxisBoundsStatus.AUTO_ADJUSTED);
        viewport.setMinY(Mem - 0.003 * Mem);
        viewport.setMaxY(Mem + 0.003 * Mem);
        //viewport.setScrollable(true);//couldn't play around this with my comp
        return rootView;
    }

    @Override
    public void onResume() {//this keeps the graph going as far as i know
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <10000;i++){//the bigger the cap the longer the graph draws
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();//method implemented later to plot stuff
                        }
                    });

                    //sleep
                    try {
                        Thread.sleep(1000);//here is the amount of time between entries
                    }catch(InterruptedException e){
                        //maneg error
                    }
                }
            }
        }).start();
    }
    private void addEntry(){
        activityManager.getMemoryInfo(memoryInfo);
        long _MEMORY = (memoryInfo.totalMem - memoryInfo.availMem) / 1024 / 1024;
        series.appendData(new DataPoint(lastX++, _MEMORY), true, 10);
    }

}