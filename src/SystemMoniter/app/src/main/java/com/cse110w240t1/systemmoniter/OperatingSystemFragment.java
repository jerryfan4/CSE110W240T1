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
public class OperatingSystemFragment extends ListFragment {

    public OperatingSystemFragment() {
    }

    public static OperatingSystemFragment newInstance(int sectionNumber) {
        OperatingSystemFragment fragment = new OperatingSystemFragment();
        Bundle args = new Bundle();
        args.putInt("OS_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.operating_system, container, false);
        String[] information = {"OS Version", "Security Patch", "Phone Model Number", "SIM Card Status", "Serial Number", "IMEI"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, information);
        setListAdapter(adapter);

        return rootView;
    }
}
