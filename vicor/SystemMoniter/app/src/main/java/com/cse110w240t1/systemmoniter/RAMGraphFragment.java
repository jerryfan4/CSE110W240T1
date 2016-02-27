package com.cse110w240t1.systemmoniter;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.ListFragment;

import android.view.ViewGroup;
import com.jjoe64.graphview.*;
import android.widget.ArrayAdapter;

/**
 * Created by victorhe on 2/27/2016.
 */
public class RAMGraphFragment extends ListFragment{
    public RAMGraphFragment(){

    }

    public static RAMGraphFragment newInstance(int sectionNumber){
        RAMGraphFragment fragment = new RAMGraphFragment();
        args.putInt("RAM_GRAPH_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GraphView graph = (GraphView)findViewById(R.id.graph);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
