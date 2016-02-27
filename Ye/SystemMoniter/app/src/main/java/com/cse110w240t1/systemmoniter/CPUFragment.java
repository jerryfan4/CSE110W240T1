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
public class CPUFragment extends ListFragment {

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
        View rootView = inflater.inflate(R.layout.gpu, container, false);
        String[] information = {"Architecture", "Make and Model", "Clock Speed", "Temperature", "Live Usage"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }


}
