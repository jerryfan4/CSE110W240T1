package com.example.victorhe.graphattempt;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

//the hardest part for me was getting to the libraries to work; I did some dumb ass shit which
//cost me hours but essentially what I did wrong was double up on the lbraries, as a result
// I had this stupid bug thta prevent the build from working; essentially we just need to drag the
//graphview.jar file into the 'libs' folder in the 'app' folder, given in project view to begin with

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;//random is used here to generate random inputs


public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();//data container
        graph.addSeries(series);//atach container
        Viewport viewport = graph.getViewport();//not sure
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMinY(4000);
        viewport.setScrollable(true);//couldn't play around this with my comp


        //following are not so related
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    //above not to related

    @Override
    protected void onResume() {//this keeps the graph going as far as i know
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <10000;i++){//the bigger the cap the longer the graph draws
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();//method implemented later to plot stuff
                        }
                    });

                    //sleep
                    try {
                        Thread.sleep(500);//here is the amount of time between entries
                    }catch(InterruptedException e){
                       //maneg error
                    }

                }
            }
        }).start();
    }

    private void addEntry(){
        series.appendData(new DataPoint(lastX++,RANDOM.nextDouble()*10d),true,10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
