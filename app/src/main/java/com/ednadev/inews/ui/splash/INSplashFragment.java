package com.ednadev.inews.ui.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ednadev.inews.R;
import dagger.android.support.DaggerFragment;

public class INSplashFragment extends DaggerFragment {

    public INSplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_splash, container, false);
    }
}