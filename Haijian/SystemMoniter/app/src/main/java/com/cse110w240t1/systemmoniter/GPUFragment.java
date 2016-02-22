package com.cse110w240t1.systemmoniter;

<pre name="code" class="java">package com.example.gpuinfo;

        import javax.microedition.khronos.egl.EGLConfig;
        import javax.microedition.khronos.opengles.GL10;
        import android.app.Activity;
        import android.opengl.GLSurfaceView;
        import android.os.Bundle;
        import android.util.Log;

public class OpenGlMainActivity extends Activity {

    private GLSurfaceView mGLSurfaceView;

    private class Renderer implements GLSurfaceView.Renderer {

        public void onDrawFrame(GL10 gl) {
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            // render
            Log.e( "GPUINFO", "GL_RENDERER:::::" + gl.glGetString( GL10.GL_RENDERER));
            // make
            Log.e( "GPUINFO", "GL_VENDOR::::: " + gl.glGetString( GL10.GL_VENDOR));
            // version
            Log.e( "GPUINFO", "GL_VERSION::::: " + gl.glGetString( GL10.GL_VERSION));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);

        // Create our surface view and set it as the content of our
        // Activity
        mGLSurfaceView = new GLSurfaceView( this);
        mGLSurfaceView.setRenderer( new Renderer());
        setContentView( mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mGLSurfaceView.onPause();
    }

}
</pre><br><br>