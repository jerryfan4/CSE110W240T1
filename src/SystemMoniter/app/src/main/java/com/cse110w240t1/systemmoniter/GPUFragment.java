package com.cse110w240t1.systemmoniter;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by fanfan on 2/7/16.
 */
public class GPUFragment extends ListFragment {
    public static String _GPU_LIVE_USAGE;

    public static String GLRenderer;
    public static String GLVender;
    public static String GLVersion;
    private GLSurfaceView mGlSurfaceView;
    public static Map<String, String>_Make;
    public static Map<String, String>_Model;

    public GPUFragement() {
        _Make = new HashMap<String, String>();
        _Model= new HashMap<String,String>();
        configureMake();
        configureModel();
    }

    private void configureMake {
        _Make.put("SM-G920F","ARM"); _Model.put("SM-G920F","Mali-T760 MP8");
        _Make.put("SM-G920I","ARM"); _Model.put("SM-G920I","Mali-T760 MP8");
        _Make.put("SM-G920T","ARM"); _Model.put("SM-G920T","Mali-T760 MP8");
        _Make.put("SM-G920P","ARM"); _Model.put("SM-G920P","Mali-T760 MP8");
        _Make.put("SM-G920A","ARM"); _Model.put("SM-G920A","Mali-T760 MP8");
        _Make.put("SM-G920S","ARM"); _Model.put("SM-G920S","Mali-T760 MP8");
        _Make.put("SM-G920V","ARM"); _Model.put("SM-G920V","Mali-T760 MP8");
        _Make.put("SM-G9200","ARM"); _Model.put("SM-G9200","Mali-T760 MP8");
        _Make.put("SM-G9208","ARM"); _Model.put("SM-G9208","Mali-T760 MP8");
        _Make.put("SM-G9209","ARM"); _Model.put("SM-G9209","Mali-T760 MP8");
        _Make.put("SM-G925F","ARM"); _Model.put("SM-G925F","Mali-T760 MP8");
        _Make.put("SM-G925A","ARM"); _Model.put("SM-G925A","Mali-T760 MP8");
        _Make.put("SM-G925T","ARM"); _Model.put("SM-G925T","Mali-T760 MP8");
        _Make.put("SM-G925V","ARM"); _Model.put("SM-G925V","Mali-T760 MP8");
        _Make.put("SM-G925S","ARM"); _Model.put("SM-G925S","Mali-T760 MP8");
        _Make.put("SM-G925I","ARM"); _Model.put("SM-G925I","Mali-T760 MP8");
        _Make.put("SM-G9250","ARM"); _Model.put("SM-G9250","Mali-T760 MP8");
        _Make.put("SM-N920A","ARM"); _Model.put("SM-N920A","Mali-T760 MP8");
        _Make.put("SM-N920T","ARM"); _Model.put("SM-N920T","Mali-T760 MP8");
        _Make.put("SM-N920V","ARM"); _Model.put("SM-N920V","Mali-T760 MP8");
        _Make.put("SM-N920S","ARM"); _Model.put("SM-N920S","Mali-T760 MP8");
        _Make.put("SM-N920P","ARM"); _Model.put("SM-N920P","Mali-T760 MP8");
        _Make.put("SM-A9000","Qualcomm"); _Model.put("SM-A9000","Adrneo 510");
        _Make.put("SM-G915A","Qualcomm"); _Model.put("SM-G915A","Adrneo 420");
        _Make.put("SM-G915T","Qualcomm"); _Model.put("SM-G915T","Adrneo 420");
        _Make.put("SM-G915V","Qualcomm"); _Model.put("SM-G915V","Adrneo 420");
        _Make.put("SM-G915P","Qualcomm"); _Model.put("SM-G915P","Adrneo 420");
        _Make.put("SM-N910A","Qualcomm"); _Model.put("SM-N910A","Adrneo 420");
        _Make.put("SM-N910T","Qualcomm"); _Model.put("SM-N910T","Adrneo 420");
        _Make.put("SM-N910V","Qualcomm"); _Model.put("SM-N910V","Adrneo 420");
        _Make.put("SM-N910S","Qualcomm"); _Model.put("SM-N910S","Adrneo 420");
        _Make.put("SM-N910P","Qualcomm"); _Model.put("SM-N910P","Adrneo 420");
        _Make.put("SM-G900A","Qualcomm"); _Model.put("SM-G900A","Adrneo 330");
        _Make.put("SM-G900T","Qualcomm"); _Model.put("SM-G900T","Adrneo 330");
        _Make.put("SM-G900V","Qualcomm"); _Model.put("SM-G900V","Adrneo 330");
        _Make.put("SM-G900S","Qualcomm"); _Model.put("SM-G900S","Adrneo 330");
        _Make.put("SM-G900P","Qualcomm"); _Model.put("SM-G900P","Adrneo 330");
        _Make.put("SM-G928I","ARM"); _Model.put("SM-G928I","Mali-T760 MP8");
        _Make.put("SM-G920A","ARM"); _Model.put("SM-G920A","Mali-T760 MP8");
        _Make.put("SM-G920T","ARM"); _Model.put("SM-G920T","Mali-T760 MP8");
        _Make.put("SM-G920P","ARM"); _Model.put("SM-G920P","Mali-T760 MP8");
        _Make.put("SM-G920V","ARM"); _Model.put("SM-G920V","Mali-T760 MP8");
        _Make.put("ONE M8","Qualcomm"); _Model.put("ONE M8","Adrneo 330");
        _Make.put("ONE E8","Qualcomm"); _Model.put("ONE E8","Adrneo 330");
        _Make.put("Desire EYE","Qualcomm"); _Model.put("Desire EYE","Adrneo 330");
        _Make.put("ONE M9","Qualcomm"); _Model.put("ONE M9","Adrneo 430");
        _Make.put("ONE A9","Qualcomm"); _Model.put("ONE A9","Adrneo 405");
        _Make.put("D6502","Qualcomm"); _Model.put("D6502","Adrneo 330");
        _Make.put("D6503","Qualcomm"); _Model.put("D6503","Adrneo 330");
        _Make.put("D6543","Qualcomm"); _Model.put("D6543","Adrneo 330");
        _Make.put("D6603","Qualcomm"); _Model.put("D6603","Adrneo 330");
        _Make.put("D6502","Qualcomm"); _Model.put("D6502","Adrneo 330");
        _Make.put("D6616","Qualcomm"); _Model.put("D6616","Adrneo 330");
        _Make.put("D6653","Qualcomm"); _Model.put("D6653","Adrneo 330");
        _Make.put("D6633","Qualcomm"); _Model.put("D6633","Adrneo 330");
        _Make.put("E6653","Qualcomm"); _Model.put("E6653","Adrneo 430");
        _Make.put("E6633","Qualcomm"); _Model.put("E6633","Adrneo 430");
        _Make.put("E6603","Qualcomm"); _Model.put("E66E3","Adrneo 430");
        _Make.put("E6633","Qualcomm"); _Model.put("E6633","Adrneo 430");
        _Make.put("E6633","Qualcomm"); _Model.put("E6633","Adrneo 430");
        _Make.put("E5823","Qualcomm"); _Model.put("E5823","Adrneo 430");
        _Make.put("E5803","Qualcomm"); _Model.put("E5803","Adrneo 430");
        _Make.put("D5833","Qualcomm"); _Model.put("D5833","Adrneo 330");
        _Make.put("D5803","Qualcomm"); _Model.put("D5803","Adrneo 330");
        _Make.put("L50t","Qualcomm"); _Model.put("L50t","Adrneo 330");
        _Make.put("L50u","Qualcomm"); _Model.put("L50u","Adrneo 330");
        _Make.put("SO-01H","Qualcomm"); _Model.put("SO-01H","Adrneo 430");
        _Make.put("SO-02H","Qualcomm"); _Model.put("SO-02H","Adrneo 430");
        _Make.put("SO-03H","Qualcomm"); _Model.put("SO-03H","Adrneo 430");
        _Make.put("SO-03G","Qualcomm"); _Model.put("SO-03G","Adrneo 430");
        _Make.put("SOV32","Qualcomm"); _Model.put("SOV32","Adrneo 430");
        _Make.put("XT1103","Qualcomm"); _Model.put("XT1103","Adrneo 420");
        _Make.put("XT1100","Qualcomm"); _Model.put("XT1100","Adrneo 420");
        _Make.put("XT1053","Qualcomm"); _Model.put("XT1053","Adrneo 330");
        _Make.put("XT1575","Qualcomm"); _Model.put("XT1575","Adrneo 418");
        _Make.put("D820","Qualcomm"); _Model.put("D820","Adrneo 330");
        _Make.put("D821","Qualcomm"); _Model.put("D821","Adrneo 330");
        _Make.put("D850","Qualcomm"); _Model.put("D850","Adrneo 330");
        _Make.put("D851","Qualcomm"); _Model.put("D851","Adrneo 330");
        _Make.put("D852","Qualcomm"); _Model.put("D852","Adrneo 330");
        _Make.put("D855","Qualcomm"); _Model.put("D855","Adrneo 330");
        _Make.put("H790","Qualcomm"); _Model.put("H790","Adrneo 418");
        _Make.put("H791","Qualcomm"); _Model.put("H791","Adrneo 418");
        _Make.put("H1511","Qualcomm"); _Model.put("H1511","Adrneo 430");
        _Make.put("H1512","Qualcomm"); _Model.put("H1512","Adrneo 430");
        _Make.put("VS985","Qualcomm"); _Model.put("VS984","Adrneo 330");
        _Make.put("H810","Qualcomm"); _Model.put("H810","Adrneo 418");
        _Make.put("H815","Qualcomm"); _Model.put("H815","Adrneo 418");
        _Make.put("H811","Qualcomm"); _Model.put("H811","Adrneo 418");
        _Make.put("F500L","Qualcomm"); _Model.put("F500L","Adrneo 418");
        _Make.put("VS986","Qualcomm"); _Model.put("VS986","Adrneo 418");
        _Make.put("LS991","Qualcomm"); _Model.put("LS991","Adrneo 418");
        _Make.put("US991","Qualcomm"); _Model.put("US991","Adrneo 418");
        _Make.put("LS996","Qualcomm"); _Model.put("LS996","Adrneo 430");
        _Make.put("H950","Qualcomm"); _Model.put("H950","Adrneo 430");

    }

    private GLSurfaceView.Renderer mGlRenderer = new GLSurfaceView.Renderer() {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            Log.d("Renderer: ", "gl renderer: " + GLES20.glGetString(GLES20.GL_RENDERER));
            Log.d("GL", "GL_VENDOR = " + GLES20.glGetString(GLES20.GL_VENDOR));
            Log.d("Version: ", "gl version: " + GLES20.glGetString(GLES20.GL_VERSION));

            GLRenderer = GLES20.glGetString(GLES20.GL_RENDERER);
            GLVender   = GLES20.glGetString(GLES20.GL_VENDOR);
            GLVersion  = GLES20.glGetString(GLES20.GL_VERSION);

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {}

        @Override
        public void onDrawFrame(GL10 gl) {}

    };

    public GPUFragment() {
    }

    public static GPUFragment newInstance(int sectionNumber) {
        GPUFragment fragment = new GPUFragment();
        Bundle args = new Bundle();
        args.putInt("GPU_Fragment", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gpu, container, false);
        String[] information = {"GL Version", "GPU Make", "GPU Model", "GPU Clock Speed", "GPU Load"};
        ArrayAdapter<String> adapter = new CustomAdapter(getActivity(), information);
        setListAdapter(adapter);

        mGlSurfaceView = new GLSurfaceView(getActivity());
        mGlSurfaceView.setRenderer(mGlRenderer);
        //getActivity().setContentView(mGlSurfaceView);

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();

                final Handler GPUUpdater = new Handler();
                final int delay = 750;

                GPUUpdater.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            RandomAccessFile reader = new RandomAccessFile("/sys/devices/platform/gpusysfs/gpu_busy", "r");
                            _GPU_LIVE_USAGE = reader.readLine() + "%";
                            reader.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        GPUUpdater.postDelayed(this, delay);
                    }
                }, delay);

                Looper.loop();
            }
        }).start();

        return rootView;
    }
}