package com.example.asafl.testingactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Animtion extends AppCompatActivity {

    private AnimationLayout mLayout;
    private static int lastDir=1;
    private int countTo11=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animation);

        mLayout = new AnimationLayout(this);

        setContentView(mLayout);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        countTo11++;
        //ignore double actions (UP & DOWN)
        if(mLayout.getPinkiballs().size()==11){
            startActivity(new Intent(this, Animtion.class));
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //logic goes here
                int a = rand(0, AnimationLayout.screenWidth);
                int b =  rand(0, AnimationLayout.screenHeight);
                if ( a >=mLayout.r.left&&a<=mLayout.r.right && b <=mLayout.r.bottom&&b>=mLayout.r.top){
                    mLayout.getPinkiballs().add(new pinkiball(
                            50, 50,
                            12,
                            450,
                            lastDir,
                            lastDir,mLayout.r));

                    lastDir *= 1;
                }else{
                    mLayout.getPinkiballs().add(new pinkiball(
                            50, 50,
                            a,
                            b,
                            lastDir,
                            lastDir,mLayout.r));

                    lastDir *= 1;
                }

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLayout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLayout.resume();
    }

    private int rand(int min, int max) {
        return (int)(Math.random() * max + min);
    }
}
