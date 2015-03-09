package com.anteastra.goodstrader.multithread;

import android.support.v4.app.FragmentActivity;

import com.anteastra.goodstrader.R;
import com.anteastra.goodstrader.gui.fragments.ControlFragment;
import com.anteastra.goodstrader.gui.fragments.TimeControlFragment;

/**
 * Created by eastra on 09.03.2015.
 */
public class UpdateUIThread implements Runnable {

    private FragmentActivity activity;

    public UpdateUIThread(FragmentActivity contextActivity) {
        activity = contextActivity;
    }

    @Override
    public void run() {

        TimeControlFragment fragmentTime = (TimeControlFragment) activity.getSupportFragmentManager().findFragmentById(R.id.fragmentTimeControl);
        if (fragmentTime != null) {
            fragmentTime.updateViews();
        }

        ControlFragment fragmentControl = (ControlFragment) activity.getSupportFragmentManager().findFragmentById(R.id.fragmentControl);
        if (fragmentControl != null) {
            fragmentControl.updateViews();
        }
    }

    public void releaseActivity() {
        activity = null;
    }

}