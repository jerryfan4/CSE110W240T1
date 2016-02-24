package com.cse110w240t1.systemmoniter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


/**
 * Created by fanfan on 2/7/16.
 *
 * Edited by artc144 on 2/15/16.
 */
public class SystemInfoFragment extends ListFragment {
    public static String _OS_VERSION;
    public static String _SECURITY_PATCH;
    public static String _PHONE_MODEL;
    public static String _MANUFACTURER;
    public static String _SIM_CARD;
    public static String _SERIAL_NUMBER;
    public static String _IMEI;

    public SystemInfoFragment() {
    }

    public static SystemInfoFragment newInstance(int sectionNumber) {
        SystemInfoFragment fragment = new SystemInfoFragment();
        Bundle args = new Bundle();
        args.putInt("OS_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.system_info, container, false);
        String[] information = {"OS Version", "Security Patch", "Phone Model", "Manufacturer", "SIM Card Status", "Serial Number", "IMEI"};

        _OS_VERSION = Build.VERSION.RELEASE;
        if (Build.VERSION.SDK_INT >= 22)
            _SECURITY_PATCH = Build.VERSION.SECURITY_PATCH;
        else
            _SECURITY_PATCH = "Unavailable at the moment";

        _PHONE_MODEL = Build.MODEL;
        _MANUFACTURER = (Build.MANUFACTURER).toUpperCase();
        _SERIAL_NUMBER = Build.SERIAL;

        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);

        _IMEI = telephonyManager.getDeviceId();

        int simStatus = telephonyManager.getSimState();

        switch (simStatus) {
            case TelephonyManager.SIM_STATE_ABSENT:
                _SIM_CARD = "no SIM card is available in the device";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                _SIM_CARD = "requires a network PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                _SIM_CARD = "requires the user's SIM PIN to unlock";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                _SIM_CARD = "requires the user's SIM PUK to unlock";
                break;
            case TelephonyManager.SIM_STATE_READY:
                _SIM_CARD = "Ready: " + telephonyManager.getSimSerialNumber();
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                _SIM_CARD = "Unknown, Signifies that the SIM is in transition between states";
                break;
            default:
                _SIM_CARD = "SIM Card status unavailable at the moment";
        }

        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }
}
