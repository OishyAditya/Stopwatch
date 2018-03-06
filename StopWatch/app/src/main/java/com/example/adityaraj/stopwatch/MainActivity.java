package com.example.adityaraj.stopwatch;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
Button start,stop,restart;
TextView textView;

    public int second=0;
    public boolean runtime;
    public boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.start);
        stop= (Button) findViewById(R.id.stop);
        restart= (Button) findViewById(R.id.Restart);
        textView= (TextView) findViewById(R.id.timeview);
        if(savedInstanceState !=null){
            second=savedInstanceState.getInt("seconds");
            runtime=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
            if (wasRunning){
                runtime=true;
            }


        }



        runtimer();

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=runtime;
        runtime=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning){
            runtime=true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds",second);
        outState.putBoolean("running",runtime);
        outState.putBoolean("wasRunning",wasRunning);
    }


    public void Setstart(View view){
        runtime=true;
    }
    public void SetStop(View view){
        runtime=false;

    }
    public void Setrestart(View view){
        runtime=false;
        second=0;

    }





               private void runtimer() {

                   final Handler handler1 = new Handler();
                   handler1.post(new Runnable() {
                       @Override
                       public void run() {
                           int hour = second / 3600;
                           int min = (second % 3600) / 60;
                           int secs = second % 60;
                           String time = String.format("%02d:%02d:%02d", hour, min, secs);
                           textView.setText(time);
                           if (runtime) {
                               second++;

                           }
                           handler1.postDelayed(this, 1000);
                       }
                   });

               }

}
