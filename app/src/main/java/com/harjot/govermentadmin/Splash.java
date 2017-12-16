package com.harjot.govermentadmin;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
ImageView im;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        im=(ImageView)findViewById(R.id.ad1);
        txt=(TextView)findViewById(R.id.ad2);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        im.startAnimation(myanim);
        txt.startAnimation(myanim);
        Thread timerThread=new Thread(){
            public void run(){
                try{
                    sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(Splash.this,Welcome.class);
                    startActivity(intent);
                    finish();


                }
            }

        };
        timerThread.start();
    }
    }

