package com.anteastra.goodstrader.gui.graphsurface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.anteastra.goodstrader.model.GameStateSingleton;

/**
 * Created by eastra on 15.03.2015.
 */
public class DrawSurface {

    Paint grayPaint;
    Paint yellowPaint;
    Paint whitePaint;


    public DrawSurface() {
        grayPaint = new Paint();
        yellowPaint = new Paint();
        whitePaint = new Paint();

        grayPaint.setColor(Color.LTGRAY);
        grayPaint.setStrokeWidth(2);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setStrokeWidth(2);

        whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(2);
    }

    private boolean showFPS = false;

    public void drawGraph(Canvas canvas) {

        if (canvas == null)
            return;

        canvas.drawColor(Color.BLACK);

        drawGraphs(canvas);
        printTextData(canvas);
    }

    private void drawGraphs(Canvas canvas) {

        GameStateSingleton state = GameStateSingleton.getInstance();

        for (int i = 0; i < (state.periodsCount - 1); i++) {
            float startY = canvas.getHeight() - (state.buyPrices[i]);
            float stopY = canvas.getHeight() - (state.buyPrices[i + 1]);
            float startX = canvas.getWidth() / 9 * i;
            float stopX = canvas.getWidth() / 9 * (i + 1);
            if (i == (state.periodsCount - 1)) {
                stopX = canvas.getWidth();
            }
            canvas.drawLine(startX, startY, stopX, stopY, grayPaint);
        }

        for (int i = 0; i < (state.periodsCount - 1); i++) {
            float startY = canvas.getHeight() - (state.sellPrices[i]);
            float stopY = canvas.getHeight() - (state.sellPrices[i + 1]);
            float startX = canvas.getWidth() / 9 * i;
            float stopX = canvas.getWidth() / 9 * (i + 1);
            if (i == (state.periodsCount - 1)) {
                stopX = canvas.getWidth();
            }
            canvas.drawLine(startX, startY, stopX, stopY, yellowPaint);
        }
    }

    private void printTextData(Canvas canvas) {
        GameStateSingleton state = GameStateSingleton.getInstance();
        canvas.drawText("$" + state.getMoneyAmt(), 10, 10, whitePaint);
        canvas.drawText(state.getCurrentDate(), 10, 25, whitePaint);
        canvas.drawText("sell:" + state.getTodaySellPrice(), canvas.getWidth() - 40, 10, whitePaint);
        canvas.drawText("buy:" + state.getTodayBuyPrice(), canvas.getWidth() - 40, 25, whitePaint);
        if (showFPS) {
            canvas.drawText("fps:" + state.getGraphFPS(), canvas.getWidth() - 40, 40, whitePaint);
        }
    }

    public void setShowFPS(boolean showFPS) {
        this.showFPS = showFPS;
    }
}
