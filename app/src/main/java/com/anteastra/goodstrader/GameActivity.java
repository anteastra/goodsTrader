package com.anteastra.goodstrader;

import java.util.Timer;
import java.util.TimerTask;

import com.anteastra.goodstrader.activitylisteners.OnMoneyChangeListener;
import com.anteastra.goodstrader.gui.ControlFragment;
import com.anteastra.goodstrader.gui.TimeControlFragment;
import com.anteastra.goodstrader.gui.GraphFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class GameActivity extends FragmentActivity implements OnMoneyChangeListener {
	
	
	private Timer mainTimer;
	private TimerTask mainTimerTask;	
	private GameStateSingleton state;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        state = GameStateSingleton.getInstance();
    	mainTimer = new Timer();
    	mainTimerTask = new MainTimerTask();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	GameStateSingleton.setContext(this);    	
		mainTimer.schedule(mainTimerTask, state.MAIN_TIMER_DURATION, state.MAIN_TIMER_DURATION);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
       
    class UpdateUIThread implements Runnable{

		@Override
		public void run() {
			GraphFragment fragment = (GraphFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentGraph);
			if (fragment != null) {
				fragment.updateViews();
			}
			
			TimeControlFragment fragmentTime = (TimeControlFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTimeControl);
			if (fragmentTime != null) {
				fragmentTime.updateViews();
			}
			
			ControlFragment fragmentControl = (ControlFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentControl);
			if (fragmentControl != null) {
				fragmentControl.updateViews();
			}
		}
		
	}
	
	class MainTimerTask extends TimerTask{
		
		private UpdateUIThread myUpdateUIThread = new UpdateUIThread();

		@Override
		public void run() {
			state.dayTimeLeft -= state.gameSpeed;
			if (state.dayTimeLeft<=0){
				state.nextDay();
				runOnUiThread(myUpdateUIThread);
			}	
		}
		
	}

	@Override
	public void onMoneyChange() {
		GraphFragment fragment = (GraphFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentGraph);
		if (fragment != null) {
			fragment.updateViews();
		}
	}	
	
}