
// custom view

package com.example.snippet.pinchtozoom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class CustomView extends View {

    private Paint paint;
    private ScaleGestureDetector scaledetector;
    private float left, top, right, bottom;
    private float x, y, scalefactor;

    // constructor
    public CustomView(Context context) {
        super(context);

        paint = new Paint();
        scaledetector = new ScaleGestureDetector(context, new ScaleListener());

        scalefactor = 1.0f;
    }

    // render screen
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // save current matrix
        canvas.save();

        // draw rectangle
        paint.setColor(0xff0000ff);
        canvas.translate(x, y);
        canvas.scale(scalefactor, scalefactor);
        canvas.drawRect(left, top, right, bottom, paint);

        // restore the matrix saved above
        canvas.restore();

        // draw text
        paint.setTextSize(30.0f);
        paint.setColor(0xff000000);
        String str = String.format("%10.6f", scalefactor);
        canvas.drawText(str, 100, 100, paint);
    }

    // screen resolution
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // rectangle position
        x = w / 2;
        y = h / 2;

        // rectangle size
        float s = w / 20;

        left   = -s;
        top    = -s;
        right  =  s;
        bottom =  s;
    }

    // touch screen event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaledetector.onTouchEvent(event);
        return true;
    }

    // listener for scale gesture
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            // get scale factor
            scalefactor *= detector.getScaleFactor();

            // keep the limit between 0.1 to 15
            scalefactor = Math.max(0.1f, Math.min( scalefactor, 15.0f));

            // refresh display
            invalidate();

            return true;
        }
    }
}
