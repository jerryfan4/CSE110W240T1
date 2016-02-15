package com.cse110w240t1.systemmoniter;

import android.os.Build;
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
    public static String _OS_VERSION;
    public static String _SECURITY_PATCH;
    public static String _PHONE_MODEL;
    public static String _MANUFACTURER;
    public static String _SIM_CARD;
    public static String _SERIAL_NUMBER;
    public static String _IMEI;

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
        String[] information = {"OS Version", "Security Patch", "Phone Model", "Manufacturer", "SIM Card Status", "Serial Number", "IMEI"};
        _OS_VERSION = Build.VERSION.BASE_OS;
        _SECURITY_PATCH = Build.VERSION.SECURITY_PATCH;
        _PHONE_MODEL = Build.MODEL;
        _MANUFACTURER = Build.MANUFACTURER;
        _SERIAL_NUMBER = Build.SERIAL;

        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }
}
