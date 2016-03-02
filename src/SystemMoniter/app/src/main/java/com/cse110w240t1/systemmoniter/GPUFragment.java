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