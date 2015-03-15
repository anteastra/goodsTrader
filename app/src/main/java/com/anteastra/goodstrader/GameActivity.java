package com.anteastra.goodstrader;

import java.util.Timer;

import com.anteastra.goodstrader.model.GameStateSingleton;
import com.anteastra.goodstrader.multithread.MainTimerTask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class GameActivity extends FragmentActivity {

    private Timer mainTimer;
    private MainTimerTask mainTimerTask;
    private GameStateSingleton state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        state = GameStateSingleton.getInstance();
        mainTimer = new Timer();
        mainTimerTask = new MainTimerTask(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainTimer.schedule(mainTimerTask, state.MAIN_TIMER_DURATION, state.MAIN_TIMER_DURATION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainTimer.cancel();
        mainTimerTask.releaseActivity();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }




}
