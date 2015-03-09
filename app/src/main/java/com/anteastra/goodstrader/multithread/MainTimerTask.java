package com.anteastra.goodstrader.multithread;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.anteastra.goodstrader.model.GameStateSingleton;

import java.util.TimerTask;

/**
 * Created by eastra on 09.03.2015.
 */
public class MainTimerTask extends TimerTask {

    private UpdateUIThread myUpdateUIThread;
    private FragmentActivity activity;

    public MainTimerTask(FragmentActivity contextActivity) {
        activity = contextActivity;
        myUpdateUIThread = new UpdateUIThread(activity);
    }

    @Override
    public void run() {
        GameStateSingleton state = GameStateSingleton.getInstance();
        state.dayTimeLeft -= state.gameSpeed;
        if (state.dayTimeLeft <= 0) {
            state.nextDay();
            activity.runOnUiThread(myUpdateUIThread);
        }
    }

    public void releaseActivity() {
        activity = null;
        myUpdateUIThread.releaseActivity();
    }
}