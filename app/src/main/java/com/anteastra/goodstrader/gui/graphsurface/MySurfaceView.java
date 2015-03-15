package com.anteastra.goodstrader.gui.graphsurface;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.anteastra.goodstrader.model.InitialGameStateSingleton;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawSurfaceThread drawSurfaceThread = null;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        drawSurfaceThread = new DrawSurfaceThread(getHolder());
        drawSurfaceThread.setRunning(true);
        drawSurfaceThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        drawSurfaceThread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                drawSurfaceThread.join();
                retry = false;

            } catch (Exception e) {
                Log.e(InitialGameStateSingleton.APP_TAG, "drawSurfaceThread finish error", e);
            }
        }
    }
}
