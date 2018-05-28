package com.example.asafl.testingactivities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class pinkiball {
    Rect r;
    private Paint mpaint , bpaint;
    private int height = 0;
    private int width = 0;
    private int x = 0;
    private int y = 0;
    private int xDirection = 1;
    private int yDirection = 1;
    private int speed = 3;
    private int factor = 1;
    public static ArrayList<Paint> paints = new ArrayList<Paint>();
    Paint pt = new Paint();
    Paint ptb = new Paint();

    public pinkiball(int height, int weight, int x, int y, int xDirection, int yDirection, Rect r) {
        pt.setStyle(Paint.Style.FILL);
        pt.setColor(Color.MAGENTA);
        ptb.setStyle(Paint.Style.FILL);
        ptb.setColor(Color.BLACK);
        this.r =r;
        this.height = height;
        this.width = weight;
        this.x = x;
        this.y = y;
        this.xDirection = xDirection;
        this.yDirection = yDirection;

        mpaint = new  Paint();
        bpaint = new  Paint();
        bpaint.setColor(0x80808080);
        mpaint.setColor(0xffffffff);
    }

    public void move(int maxH, int maxW){
        //todo: check if we hit boundaries/tangela
        if ( x >=r.left&&x<=r.right &&  y <=r.bottom&&y>=r.top){
            yDirection *=-1;
            xDirection *= -1;
        }
        if (y >= maxH || y < 0 )
            yDirection *= -1;
        if(x >= maxW || x < 0 )
            xDirection *= -1;

        x += xDirection * speed * factor;
        y += yDirection * speed * factor;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void draw(Canvas canvas, int maxH, int maxW) {

        canvas.drawCircle(x, y, 50, ptb);
        move(maxH, maxW);
        canvas.drawCircle(x, y, 40, pt);

    }

    private static Paint randPaints() {
        return paints.get((int)(Math.random() * 1 + (paints.size()-1)));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
