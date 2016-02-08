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
public class RAMFragment extends ListFragment {

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
        String[] information = {"Make and Model", "Speed", "Capacity", "Live Usage"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, information);
        setListAdapter(adapter);

        return rootView;
    }

}
