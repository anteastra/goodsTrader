package com.anteastra.goodstrader.multithread;

import android.view.SurfaceHolder;

/**
 * Created by eastra on 15.03.2015.
 */
public class MainThread implements Runnable{

    SurfaceHolder graphSurfaceHolder;


    public MainThread(SurfaceHolder graphSurfaceHolder) {
        this.graphSurfaceHolder = graphSurfaceHolder;
    }

    @Override
    public void run() {

    }
}
