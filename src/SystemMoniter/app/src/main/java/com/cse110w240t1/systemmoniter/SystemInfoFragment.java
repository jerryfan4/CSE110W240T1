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

    public String getOS_Version(){
        if (Build.VERSION.RELEASE != null)
          return Build.VERSION.RELEASE;
        else
          return "Unavailable at the moment";
    }

    public String getSecurity_patch() {
        if (Build.VERSION.SDK_INT >= 22)
            return Build.VERSION.SECURITY_PATCH;
        else
            return "Unavailable at the moment";
    }

    public String getPhone_model() {
        if (Build.MODEL != null)
            return Build.MODEL;
        else
            return "Unavailable at the moment";
    }

    public String getManufacturer() {
        if (Build.MANUFACTURER != null)
            return (Build.MANUFACTURER).toUpperCase();
        else
            return "Unavailable at the moment";
    }

    public String getSIMCard() {
        if (getContext() == null) return "Unavailable at the moment";
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        int simStatus = telephonyManager.getSimState();

        switch (simStatus) {
            case TelephonyManager.SIM_STATE_ABSENT:
                return "no SIM card is available in the device";
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return "requires a network PIN to unlock";
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return "requires the user's SIM PIN to unlock";
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return "requires the user's SIM PUK to unlock";
            case TelephonyManager.SIM_STATE_READY:
                return "Ready: " + telephonyManager.getSimSerialNumber();
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return "Unknown, Signifies that the SIM is in transition between states";
            default:
                return "SIM Card status unavailable at the moment";
        }
    }

    public String getSerialNumber() {
        if (Build.SERIAL != null)
            return Build.SERIAL;
        else
            return "Unavailable at the moment";
    }

    public String getIMEI() {
        if (getContext() == null) return "Unavailable at the moment";
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.system_info, container, false);
        String[] information = {"OS Version", "Security Patch", "Phone Model", "Manufacturer", "SIM Card Status", "Serial Number", "IMEI"};

        _OS_VERSION = getOS_Version();
        _SECURITY_PATCH = getSecurity_patch();
        _PHONE_MODEL = getPhone_model();
        _MANUFACTURER = getManufacturer();
        _SIM_CARD = getSIMCard();
        _SERIAL_NUMBER = getSerialNumber();
        _IMEI = getIMEI();

        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        return rootView;
    }
}
