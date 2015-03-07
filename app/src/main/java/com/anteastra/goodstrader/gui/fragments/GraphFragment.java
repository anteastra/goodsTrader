package com.anteastra.goodstrader.gui.fragments;

import com.anteastra.goodstrader.R;
import com.anteastra.goodstrader.gui.graphsurface.MySurfaceView;
import com.anteastra.goodstrader.model.GameStateSingleton;
import com.anteastra.goodstrader.model.InitialGameStateSingleton;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GraphFragment extends Fragment {

    private View rootView;
    private MySurfaceView surface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(InitialGameStateSingleton.APP_TAG, "GraphFragment onCreate done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        SurfaceView surfaceViewMain = (SurfaceView) rootView.findViewById(R.id.surfaceViewMain);
        LinearLayout lay = (LinearLayout) rootView.findViewById(R.id.linearLayoutSurface);
        surface = new MySurfaceView(this.getActivity());
        ViewGroup.LayoutParams p = surfaceViewMain.getLayoutParams();
        surface.setLayoutParams(p);
        lay.addView(surface);
        lay.removeView(surfaceViewMain);

        Log.v(InitialGameStateSingleton.APP_TAG, "GraphFragment onCreateView done");

        return rootView;
    }
}
