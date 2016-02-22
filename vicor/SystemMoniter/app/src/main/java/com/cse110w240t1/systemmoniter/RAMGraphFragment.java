package com.cse110w240t1.systemmoniter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;

/**
 * Created by victorhe on 2/21/2016.
 */
public class RAMGraphFragment extends Activity{
    private float[] values = new float[60];
    private String[] verlabels = new String[] { "600","500","400","300","200","100","80","60","40","20","0", };
    private String[] horlabels = new String[] { "0","10", "20", "30", "40", "50", "60"};
    private GraphView graphView;
    private LinearLayout graph;
    private boolean runnable = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ram_layout);

        graph = (LinearLayout)findViewById(R.id.ramG);
        graphView = new GraphView(RAMGraphFragment.this, values, "TEST GRAPH", horlabels, verlabels, GraphView.LINE);
        graph.addView(graphView);
        runnable = true;
        startDraw.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        runnable = false;
    }

    public void setGraph(int data){
        for(int i=0; i<values.length-1; i++){
            values[i] = values[i+1];
        }

        values[values.length-1] = (float)data;
        graph.removeView(graphView);
        graph.addView(graphView);
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg){
            switch(msg.what){

                case 0x01:
                    int testValue = (int)(Math.random() * 600)+1;
                    setGraph(testValue);
                    break;
            }
        }
    };

    public Thread startDraw = new Thread() {
        @Override
        public void run() {
            while (runnable) {
                handler.sendEmptyMessage(0x01);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //e.printstacktrace();
                }
            }
        }
    };
}
