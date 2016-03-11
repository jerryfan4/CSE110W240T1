package com.cse110w240t1.systemmoniter;

import android.os.Build;
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
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fanfan on 2/7/16.
 */
public class CPUFragment extends ListFragment {

    private static final String CLOCK_SPEED_1 =
            "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"; //first core
    private static final String CLOCK_SPEED_4 =
            "/sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq"; //last core for dual duo-core cpus
    private static final String CLOCK_SPEED_8 =
            "/sys/devices/system/cpu/cpu7/cpufreq/cpuinfo_max_freq"; //last core for dual quad-core cpus
    private static final String NUM_OF_CORES = "/sys/devices/system/cpu/kernel_max";

    private static final int QUAD_CORE = 3;
    private static final int OCTA_CORE = 7;

    public static String _CPU_ARCHITECTURE;
    public static String _CPU_USAGE;
    public static String _CPU_MAKE;
    public static String _CPU_MODEL;
    public static String _CPU_CLOCK_SPEED;
    public static Map<String, String> _MAKE;
    public static Map<String, String> _MODEL;
  

    public CPUFragment() {
        _MAKE = new HashMap<String, String>();
        _MODEL = new HashMap<String, String>();
        configureMakeNModel();
    }

    private void configureMakeNModel() {
        _MAKE.put("SM-G920F", "Samsung"); _MODEL.put("SM-G920F", "Exynos 7420 Octa");
        _MAKE.put("SM-G920I", "Samsung"); _MODEL.put("SM-G920I", "Exynos 7420 Octa");
        _MAKE.put("SM-G920T", "Samsung"); _MODEL.put("SM-G920T", "Exynos 7420 Octa");
        _MAKE.put("SM-G920P", "Samsung"); _MODEL.put("SM-G920P", "Exynos 7420 Octa");
        _MAKE.put("SM-G925F", "Samsung"); _MODEL.put("SM-G925F", "Exynos 7420 Octa");
        _MAKE.put("SM-G925I", "Samsung"); _MODEL.put("SM-G925I", "Exynos 7420 Octa");
        _MAKE.put("SM-G920A", "Samsung"); _MODEL.put("SM-G920A", "Exynos 7420 Octa");
        _MAKE.put("SM-G9200", "Samsung"); _MODEL.put("SM-G9200", "Exynos 7420 Octa");
        _MAKE.put("SM-G9208", "Samsung"); _MODEL.put("SM-G9208", "Exynos 7420 Octa");
        _MAKE.put("SM-G9208/SS", "Samsung"); _MODEL.put("SM-G9208/SS", "Exynos 7420 Octa");
        _MAKE.put("SM-G9209", "Samsung"); _MODEL.put("SM-G9209", "Exynos 7420 Octa");
        _MAKE.put("SM-G920S", "Samsung"); _MODEL.put("SM-G920S", "Exynos 7420 Octa");
        _MAKE.put("SM-G925A", "Samsung"); _MODEL.put("SM-G925A", "Exynos 7420 Octa");
        _MAKE.put("SM-G925T", "Samsung"); _MODEL.put("SM-G925T", "Exynos 7420 Octa");
        _MAKE.put("SM-G9250", "Samsung"); _MODEL.put("SM-G9250", "Exynos 7420 Octa");
        _MAKE.put("SM-G920V", "Samsung"); _MODEL.put("SM-G920V", "Exynos 7420 Octa");

        _MAKE.put("SM-N920T", "Samsung"); _MODEL.put("SM-N920T", "Exynos 7420 Octa");
        _MAKE.put("SM-N920A", "Samsung"); _MODEL.put("SM-N920A", "Exynos 7420 Octa");
        _MAKE.put("SM-N920P", "Samsung"); _MODEL.put("SM-N920P", "Exynos 7420 Octa");
        _MAKE.put("SM-N920R", "Samsung"); _MODEL.put("SM-N920R", "Exynos 7420 Octa");
        _MAKE.put("SM-N920V", "Samsung"); _MODEL.put("SM-N920V", "Exynos 7420 Octa");
        _MAKE.put("SM-N9200", "Samsung"); _MODEL.put("SM-N9200", "Exynos 7420 Octa");

        _MAKE.put("SM-A9000", "Qualcomm"); _MODEL.put("SM-A9000", "MSM8976");
        
        _MAKE.put("SM-G915A", "Qualcomm"); _MODEL.put("SM-G915A", "APQ8084");
        _MAKE.put("SM-G915T", "Qualcomm"); _MODEL.put("SM-G915T", "APQ8084");
        _MAKE.put("SM-G915R", "Qualcomm"); _MODEL.put("SM-G915R", "APQ8084");
        _MAKE.put("SM-G915G", "Qualcomm"); _MODEL.put("SM-G915G", "APQ8084");
        _MAKE.put("SM-G915S", "Qualcomm"); _MODEL.put("SM-G915S", "APQ8084");

        _MAKE.put("SM-N910T", "Qualcomm"); _MODEL.put("SM-N910T", "APQ8084");
        _MAKE.put("SM-N910A", "Qualcomm"); _MODEL.put("SM-N910A", "APQ8084");
        _MAKE.put("SM-N910P", "Qualcomm"); _MODEL.put("SM-N910P", "APQ8084");
        _MAKE.put("SM-N910R", "Qualcomm"); _MODEL.put("SM-N910R", "APQ8084");
        _MAKE.put("SM-N910V", "Qualcomm"); _MODEL.put("SM-N910V", "APQ8084");
        _MAKE.put("SM-N910C", "Samsung"); _MODEL.put("SM-N910C", "Exynos 5433");

        _MAKE.put("SM-N900A", "Qualcomm"); _MODEL.put("SM-N910A", "MSM8974");
        _MAKE.put("SM-N900V", "Qualcomm"); _MODEL.put("SM-N910V", "MSM8974");
        _MAKE.put("SM-N900T", "Qualcomm"); _MODEL.put("SM-N910T", "MSM8974");
        _MAKE.put("SM-N900P", "Qualcomm"); _MODEL.put("SM-N910P", "MSM8974");
        _MAKE.put("SM-N900R", "Qualcomm"); _MODEL.put("SM-N910R", "MSM8974");

        _MAKE.put("SM-N928A", "Samsung"); _MODEL.put("SM-N928A", "Exynos 7420 Octa");
        _MAKE.put("SM-N928R", "Samsung"); _MODEL.put("SM-N928R", "Exynos 7420 Octa");
        _MAKE.put("SM-N928T", "Samsung"); _MODEL.put("SM-N928T", "Exynos 7420 Octa");
        _MAKE.put("SM-N928P", "Samsung"); _MODEL.put("SM-N928P", "Exynos 7420 Octa");
        _MAKE.put("SM-N928V", "Samsung"); _MODEL.put("SM-N928V", "Exynos 7420 Octa");

        _MAKE.put("ONE A9", "Qualcomm"); _MODEL.put("ONE A9", "MSM8952");
        _MAKE.put("ONE M9", "Qualcomm"); _MODEL.put("ONE M9", "MSM8994");
        _MAKE.put("Desire EYE", "Qualcomm"); _MODEL.put("Desire EYE", "MSM8974");
        _MAKE.put("ONE M8", "Qualcomm"); _MODEL.put("ONE M8", "MSM8974");
        _MAKE.put("ONE E8", "Qualcomm"); _MODEL.put("ONE E8", "MSM8974");

        _MAKE.put("D6502", "Qualcomm"); _MODEL.put("D6502", "MSM8974");
        _MAKE.put("D6503", "Qualcomm"); _MODEL.put("D6503", "MSM8974");
        _MAKE.put("D6543", "Qualcomm"); _MODEL.put("D6543", "MSM8974");
        _MAKE.put("L50t", "Qualcomm"); _MODEL.put("L50t", "MSM8974");
        _MAKE.put("L50u", "Qualcomm"); _MODEL.put("L50u", "MSM8974");

        _MAKE.put("D6603", "Qualcomm"); _MODEL.put("D6603", "MSM8974");
        _MAKE.put("D6653", "Qualcomm"); _MODEL.put("D6653", "MSM8974");
        _MAKE.put("D6616", "Qualcomm"); _MODEL.put("D6616", "MSM8974");
        _MAKE.put("D6633", "Qualcomm"); _MODEL.put("D6633", "MSM8974");
        _MAKE.put("D5803", "Qualcomm"); _MODEL.put("D5803", "MSM8974");

        _MAKE.put("D5833", "Qualcomm"); _MODEL.put("D5833", "MSM8974");
        _MAKE.put("E6603", "Qualcomm"); _MODEL.put("E6603", "MSM8994");
        _MAKE.put("E6633", "Qualcomm"); _MODEL.put("E6633", "MSM8994");
        _MAKE.put("E6653", "Qualcomm"); _MODEL.put("E6653", "MSM8994");
        _MAKE.put("E6683", "Qualcomm"); _MODEL.put("E6682", "MSM8994");

        _MAKE.put("SO-01H", "Qualcomm"); _MODEL.put("SO-01H", "MSM8974");
        _MAKE.put("SOV32", "Qualcomm"); _MODEL.put("SOV32", "MSM8994");
        _MAKE.put("E5803", "Qualcomm"); _MODEL.put("E5803", "MSM8994");
        _MAKE.put("E5823", "Qualcomm"); _MODEL.put("E5823", "MSM8994");
        _MAKE.put("SO-02H", "Qualcomm"); _MODEL.put("SO-02H", "MSM8994");

        _MAKE.put("E6833", "Qualcomm"); _MODEL.put("E6833", "MSM8994");
        _MAKE.put("E6853", "Qualcomm"); _MODEL.put("E6853", "MSM8994");
        _MAKE.put("E6883", "Qualcomm"); _MODEL.put("E6883", "MSM8994");
        _MAKE.put("SO-03H", "Qualcomm"); _MODEL.put("SO-03H", "MSM8994");
        _MAKE.put("E6533", "Qualcomm"); _MODEL.put("E6533", "MSM8994");

        _MAKE.put("SO-03G", "Qualcomm"); _MODEL.put("SO-03G", "MSM8994");
        _MAKE.put("XT1103", "Qualcomm"); _MODEL.put("XT1103", "APQ8084");
        _MAKE.put("XT1100", "Qualcomm"); _MODEL.put("XT1100", "APQ8084");
        _MAKE.put("D820", "Qualcomm"); _MODEL.put("D820", "MSM8974");

        _MAKE.put("D821", "Qualcomm"); _MODEL.put("D821", "MSM8974");
        _MAKE.put("H790", "Qualcomm"); _MODEL.put("H790", "MSM8992");
        _MAKE.put("H791", "Qualcomm"); _MODEL.put("H791", "MSM8992");
        _MAKE.put("H1511", "Qualcomm"); _MODEL.put("H1511", "MSM8994");
        _MAKE.put("H1512", "Qualcomm"); _MODEL.put("H1512", "MSM8994");

        _MAKE.put("XT1575", "Qualcomm"); _MODEL.put("XT1575", "MSM8992");
        _MAKE.put("XT1053", "Qualcomm"); _MODEL.put("XT1053", "MSM8974");
        _MAKE.put("D850", "Qualcomm"); _MODEL.put("D850", "MSM8974");
        _MAKE.put("D851", "Qualcomm"); _MODEL.put("D851", "MSM8974");
        _MAKE.put("VS985", "Qualcomm"); _MODEL.put("VS985", "MSM8974");

        _MAKE.put("D855", "Qualcomm"); _MODEL.put("D855", "MSM8974");
        _MAKE.put("H815", "Qualcomm"); _MODEL.put("H815", "MSM8974");
        _MAKE.put("H811", "Qualcomm"); _MODEL.put("H811", "MSM8992");
        _MAKE.put("H810", "Qualcomm"); _MODEL.put("H810", "MSM8992");
        _MAKE.put("D852", "Qualcomm"); _MODEL.put("D852", "MSM8992");

        _MAKE.put("F500L", "Qualcomm"); _MODEL.put("F500L", "MSM8992");
        _MAKE.put("VS986", "Qualcomm"); _MODEL.put("VS986", "MSM8992");
        _MAKE.put("LS991", "Qualcomm"); _MODEL.put("LS991", "MSM8992");
        _MAKE.put("US991", "Qualcomm"); _MODEL.put("US991", "MSM8992");
        _MAKE.put("LS996", "Qualcomm"); _MODEL.put("LS996", "MSM8994");
        _MAKE.put("H950", "Qualcomm"); _MODEL.put("H950", "MSM8994");
    }

    public static CPUFragment newInstance(int sectionNumber) {
        CPUFragment fragment = new CPUFragment();
        Bundle args = new Bundle();
        args.putInt("CPU_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public String getMake() {
        if (_MAKE.get(Build.MODEL) != null) {
            return _MAKE.get(Build.MODEL);
        } else {
            return "Unavailable at the moment";
        }
    }

    public String getModel() {
        if (_MODEL.get(Build.MODEL) != null) {
            return _MODEL.get(Build.MODEL);
        } else {
            return  "Unavailable at the moment";
        }
    }

    public String getArchitecture() {
        if ( Build.VERSION.SDK_INT >= 21 ) {
            return Build.SUPPORTED_ABIS[0];
        }
        else
        {
            //return Build.CPU_ABI;
            return "Unavailable at the moment";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cpu, container, false);
        String[] information = {"CPU Architecture", "CPU Make", "CPU Model", "CPU Clock Speed", "CPU Load"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        //get CPU Clock Speed
        File a = new File(CLOCK_SPEED_1);
        File b = new File(CLOCK_SPEED_1);
        File c = new File(NUM_OF_CORES);

        int numCores = 0;

        try {
            RandomAccessFile reader = new RandomAccessFile(c, "r");
            numCores = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( numCores == QUAD_CORE ) {
            b = new File(CLOCK_SPEED_4);
        }
        else {
            if ( numCores == OCTA_CORE ) {
                b = new File(CLOCK_SPEED_8);
            }
        }

        if ( !a.exists() ) {
            _CPU_CLOCK_SPEED = "Unavailable";
        }
        else {
            if (b.exists() && !b.isDirectory()) {
                getCpuClock2(a, b);
            } else {
                getCpuClock1(a);
            }
        }


        //get CPU make and model
        _CPU_MAKE = getMake();
        _CPU_MODEL = getModel();
        _CPU_ARCHITECTURE = getArchitecture();

        //get CPU live usage
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();

                final Handler CPUUpdater = new Handler();
                final int delay = 750;

                CPUUpdater.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _CPU_USAGE = getCpuUsage() + "%";

                        CPUUpdater.postDelayed(this, delay);
                    }
                }, delay);

                Looper.loop();
            }
        }).start();

        return rootView;
    }


    //method to help get cpu clock speed for single cpu SoC
    private void getCpuClock1( final File a ) {
        try {
            RandomAccessFile reader = new RandomAccessFile(a, "r");
                double clock_speed = Long.parseLong(reader.readLine());
                clock_speed /= 1000000; //convert Hz to GHz
                _CPU_CLOCK_SPEED = clock_speed + " GHz";
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to help get cpu clock speed for dual cpu SoC
    private void getCpuClock2( final File a, final File b ) {

        double clock_speed1 = 0; //clock speed for 1st cpu
        double clock_speed2 = 0; //clock speed for 2nd cpu

        //for first cpu
        try {
            RandomAccessFile reader = new RandomAccessFile(a, "r");
            clock_speed1 = Double.parseDouble(reader.readLine());
            clock_speed1 /= 1000000; //convert Hz to GHz
            _CPU_CLOCK_SPEED = clock_speed1 + " GHz";
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //for second cpu
        try {
            RandomAccessFile reader = new RandomAccessFile(b, "r");
            clock_speed2 = Double.parseDouble(reader.readLine());
            clock_speed2 /= 1000000; //convert Hz to GHz
            if ( clock_speed2 != clock_speed1) {
                _CPU_CLOCK_SPEED += ", " + clock_speed2 + " GHz";
                _CPU_CLOCK_SPEED += " (dual set of CPUs)";
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to help get cpu live usage
    private int getCpuUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");

            //get first measurement for cpu usage statistics
            String cpuLoad = reader.readLine();

            String[] fields = cpuLoad.split("\\s+");  // Split on one or more spaces

            long idle1 = Long.parseLong(fields[4]);
            long cpuMeasure1 = Long.parseLong(fields[1]) + Long.parseLong(fields[2]) +
                    Long.parseLong(fields[3]) + Long.parseLong(fields[5]) +
                    Long.parseLong(fields[6]) + Long.parseLong(fields[7]) +
                    Long.parseLong(fields[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            //get second measurement for cpu usage statistics
            reader.seek(0);
            cpuLoad = reader.readLine();
            reader.close();

            fields = cpuLoad.split("\\s+");

            long idle2 = Long.parseLong(fields[4]);
            long cpuMeasure2 = Long.parseLong(fields[1]) + Long.parseLong(fields[2]) +
                    Long.parseLong(fields[3]) + Long.parseLong(fields[5]) +
                    Long.parseLong(fields[6]) + Long.parseLong(fields[7]) +
                    Long.parseLong(fields[8]);

            //calculate cpu usage
            double cpuUsage =  (100 * ( (double)(cpuMeasure2 - cpuMeasure1) /
                    ((cpuMeasure2 + idle2) - (cpuMeasure1 + idle1)) ));

            return (int)cpuUsage;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
