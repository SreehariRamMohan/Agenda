package jrjava.eduhub;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.PopupWindow;

public class Stopwatch extends AppCompatActivity {
    private Button test;
    private PopupWindow popUpWindow;
    private LayoutInflater inflater;




    Button start;
    Button stop;
    Button reset;
    Chronometer c;
    Chronometer focus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.learn);
        reset = (Button)findViewById(R.id.reset);
        c = (Chronometer)findViewById(R.id.chronometer1);
        focus=(Chronometer)findViewById(R.id.chronometer1);






        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.start();
            }
        });
       /*stop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               c.stop();
               final long pauseTime = System.currentTimeMillis();
           }
       }); */




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                focus.setBase(SystemClock.uptimeMillis());
            }
        });
    }




    public void showLearningBox(View view) {


    }
}