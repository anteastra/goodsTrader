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
    private GameStateSingleton state;

    private int x = 0;


    public DrawSurfaceThread(SurfaceHolder sh) {
        surfaceHolder = sh;
        state = GameStateSingleton.getInstance();
    }

    @Override
    public void run() {
        Canvas canvas;

        while (isRunning) {
            long now = System.currentTimeMillis();
            long elapsedTime = now - prevTime;
            if (elapsedTime > 50) {
                prevTime = now;

                if (x > 100) x = 0;
                x++;

                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        drawGraph(canvas);
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

    public void drawGraph(Canvas canvas) {

        if (canvas == null) return;
        Paint myPaint = new Paint();

        myPaint.setColor(Color.LTGRAY);
        myPaint.setStrokeWidth(2);

        canvas.drawColor(Color.BLACK);

        for (int i = 0; i < (state.periodsCount - 1); i++) {
            float startY = canvas.getHeight() - (state.buyPrices[i]);
            float stopY = canvas.getHeight() - (state.buyPrices[i + 1]);
            float startX = canvas.getWidth() / 9 * i;
            float stopX = canvas.getWidth() / 9 * (i + 1);
            if (i == (state.periodsCount - 1)) {
                stopX = canvas.getWidth();
            }
            canvas.drawLine(startX, startY, stopX, stopY, myPaint);
        }

        myPaint.setColor(Color.YELLOW);

        for (int i = 0; i < (state.periodsCount - 1); i++) {
            float startY = canvas.getHeight() - (state.sellPrices[i]);
            float stopY = canvas.getHeight() - (state.sellPrices[i + 1]);
            float startX = canvas.getWidth() / 9 * i;
            float stopX = canvas.getWidth() / 9 * (i + 1);
            if (i == (state.periodsCount - 1)) {
                stopX = canvas.getWidth();
            }
            canvas.drawLine(startX, startY, stopX, stopY, myPaint);
        }

        myPaint.setColor(Color.WHITE);
        printTextData(canvas, myPaint);
    }

    private void printTextData(Canvas canvas, Paint myPaint) {
        canvas.drawText("$" + state.getMoneyAmt(), 10, 10, myPaint);
        canvas.drawText(state.getCurrentDate(), 10, 25, myPaint);
        canvas.drawText("sell:" + state.getTodaySellPrice(), canvas.getWidth() - 40, 10, myPaint);
        canvas.drawText("buy:" + state.getTodayBuyPrice(), canvas.getWidth() - 40, 25, myPaint);
    }

    public void setRunning(boolean bool) {
        isRunning = bool;
    }

}
