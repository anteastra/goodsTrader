package com.anteastra.goodstrader.gui.graphsurface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.anteastra.goodstrader.model.GameStateSingleton;

public class DrawSurfaceThread extends Thread {

    private boolean isRunning = false;
    private long prevTime = 0;
    private SurfaceHolder surfaceHolder;
    private DrawSurface drawSurface;
    private int fpsCounter;


    public DrawSurfaceThread(SurfaceHolder sh) {
        surfaceHolder = sh;
    }

    @Override
    public void run() {
        Canvas canvas;
        drawSurface = new DrawSurface();
        drawSurface.setShowFPS(true);

        long startedSecond = System.currentTimeMillis();
        while (isRunning) {
            long now = System.currentTimeMillis();
            long elapsedTime = now - prevTime;
            if (now > startedSecond + 1000) {
                GameStateSingleton.getInstance().setGraphFPS(1000/(fpsCounter));
                fpsCounter = 0;
                startedSecond = now;
            }
            if (elapsedTime > 50) {
                prevTime = now;

                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        drawSurface.drawGraph(canvas);
                        fpsCounter++;
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            } else {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setRunning(boolean bool) {
        isRunning = bool;
    }

}
