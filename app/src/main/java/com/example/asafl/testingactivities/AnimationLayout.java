package com.example.asafl.testingactivities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class AnimationLayout extends SurfaceView implements  Runnable{
    private Canvas animcanvas;
    public Rect r;
    private static ArrayList<pinkiball> pinkiballs = new ArrayList<>();
    private SurfaceHolder surfaceHolder;
    private boolean canWePlay = false;
    private Thread mThread;
    public static int screenWidth, screenHeight;

    public AnimationLayout(Context context) {

        super(context);
        surfaceHolder = getHolder();


    }

    @Override
    public void run() {

        while(canWePlay) {

            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }
            animcanvas = surfaceHolder.lockCanvas();
            screenHeight = animcanvas.getHeight();
            screenWidth = animcanvas.getWidth();

            //draw goes here:

            Paint bgPaint = new Paint();
            bgPaint.setColor(Color.DKGRAY);
            bgPaint.setStyle(Paint.Style.FILL);

             r = new Rect(300, 300, screenWidth-300, screenHeight-300);
            animcanvas.drawRect(r, bgPaint);


            for(pinkiball t : pinkiballs){

                t.draw(animcanvas, getHeight(), getWidth());
            }
//            RectF oval = new RectF();
//            animcanvas.drawArc(oval, 0F, 360F, true, ballPaint);




            surfaceHolder.unlockCanvasAndPost(animcanvas);
        }
    }

    public void pause() {
        canWePlay = false;
        pinkiballs.clear();
        while(true){
            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }

        mThread = null;
    }

    public void resume() {
        canWePlay = true;
        mThread = new Thread(this);
        mThread.start();
    }
    public void clean(){

    }

    public static ArrayList<pinkiball> getPinkiballs() {
        return pinkiballs;
    }
}
