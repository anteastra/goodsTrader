package com.anteastra.goodstrader.gui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anteastra.goodstrader.R;

import com.anteastra.goodstrader.model.GameStateSingleton;
import com.anteastra.goodstrader.model.InitialGameStateSingleton;


public class TimeControlFragment extends Fragment{
	
	private View rootView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(InitialGameStateSingleton.APP_TAG, "TimeControlFragment onCreate done");
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_timecontrol, container, false);
                
        updateViews();
        initButtons();
        
        Log.v(InitialGameStateSingleton.APP_TAG, "TimeControlFragment onCreateView done");        
        return rootView;
    }
	
	private void initButtons() {
		((ImageButton)rootView.findViewById(R.id.buttonSpeed0)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStateSingleton.getInstance().gameSpeed = GameStateSingleton.getInstance().SPEED_0;
                updateViews();
            }
        });
		((ImageButton)rootView.findViewById(R.id.buttonSpeed1)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GameStateSingleton.getInstance().gameSpeed = GameStateSingleton.getInstance().SPEED_1;
				updateViews();
			}
		});
		((ImageButton)rootView.findViewById(R.id.buttonSpeed2)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GameStateSingleton.getInstance().gameSpeed = GameStateSingleton.getInstance().SPEED_2;
				updateViews();
			}
		});
		((ImageButton)rootView.findViewById(R.id.buttonSpeed3)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GameStateSingleton.getInstance().gameSpeed = GameStateSingleton.getInstance().SPEED_3;
				updateViews();
			}
		});
	}

	public void updateViews(){
		
		GameStateSingleton state = GameStateSingleton.getInstance();
				
		((TextView) rootView.findViewById(R.id.textViewSpeed)).setText(String.valueOf(state.gameSpeed));
		rootView.findViewById(R.id.textViewSpeed).invalidate();
		
		
	}
}