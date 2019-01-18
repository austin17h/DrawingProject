package com.example.a1474672.drawingproject;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SquareView extends View {
    /* ------------------------*/
    /*    member variables     */
    private List<Point> mCirclePointsWhite;
    private List<Point> mCirclePointsRed;
    private List<Point> mCirclePointsBlue;
    private List<Point> mCirclePointsYellow;
    private List<PointStorage> mCirclePoints;
    private int on;
    private int rad;
    private Handler mHandler;
    private DissolveOps mDissolveOps;
    boolean isdissolved;


    private Paint mPaint;

    /* ------------------------*/
    /*    constructor          */

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        on = 0;
        mHandler = new Handler();
        mCirclePointsWhite = new ArrayList<>();
        mCirclePointsYellow = new ArrayList<>();
        mCirclePointsBlue = new ArrayList<>();
        mCirclePointsRed = new ArrayList<>();
        mCirclePoints = new ArrayList<>();
        isdissolved = true;
        setupPaint();
    }

    /* ------------------------*/
    /*    class methods        */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeCallbacks(mDissolveOps);
                float touchX = event.getX();
                float touchY = event.getY();
                PointStorage putin = new PointStorage((new Point(Math.round(touchX), Math.round(touchY))), on, rad);
                mCirclePoints.add(putin);
                postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float touchmX = event.getX();
                float touchmY = event.getY();
                PointStorage puting = new PointStorage((new Point(Math.round(touchmX), Math.round(touchmY))), on, rad);
                mCirclePoints.add(puting);
                postInvalidate();
                break;
        }
        if(isdissolved)
        {
            Log.i("isdissolved", "!!!");
            if(action == MotionEvent.ACTION_UP)
            {
                Log.i("isdissolved", "???");
                dissolvePoints();
            }
        }
        return true;
    }


    /* --------------------------------*/
    /*    protected draw operation     */

    @Override
    protected void onDraw(Canvas canvas) {
        int radius;
        for (PointStorage ps : mCirclePoints) {
            radius = ps.getSize();
            if (ps.getColor() == 0) {
                mPaint.setColor(Color.WHITE);
            } else if (ps.getColor() == 2)
                mPaint.setColor(Color.RED);
            else if (ps.getColor() == 1)
                mPaint.setColor(Color.BLUE);
            else if (ps.getColor() == 3)
                mPaint.setColor(Color.YELLOW);
            else if(ps.getColor() == 4)
                mPaint.setColor(Color.BLACK);
            canvas.drawCircle(ps.getPoint().x, ps.getPoint().y, radius, mPaint);
        }
    }

    public void setChecked(boolean x) {
        isdissolved = x;
    }
    public void stopDissolving() {
        mHandler.removeCallbacks(mDissolveOps);
    }
    public void startDissolving() {
        dissolvePoints();
    }

    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        mPaint.setStrokeWidth(10f);
        mPaint.setStyle(Paint.Style.FILL);
        rad = 10;
    }

    public void changeColor(int id) {
        on = id;
    }

    public void changeSize(int size) {
        rad = size;
    }

    public void clearCircles() {
        mCirclePoints.clear();
        postInvalidate();
    }

    private void dissolvePoints() {
        mDissolveOps = new DissolveOps();
        mHandler.postDelayed(mDissolveOps, 150);

    }

    public class DissolveOps implements Runnable {

        public DissolveOps() {
        }

        @Override
        public void run() {
            ListIterator<PointStorage> itr = mCirclePoints.listIterator();
            PointStorage p = null;
            while (itr.hasNext()) {
                p = itr.next();
                if (p.getColor() != 0) {
                    int sizing = ThreadLocalRandom.current().nextInt(-3, 2);
                    /*int offsetX = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                    int offsetY = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                    p.getPoint().offset(offsetX, offsetY);*/
                    p.setSize(p.getSize() + sizing);
                    if (p.getSize() <= 0)
                        itr.remove();
                }

            }
            postInvalidate();
            mHandler.postDelayed(this, 150);
        }
    }
}
