package com.ednadev.inews.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ednadev.inews.R;
import com.ednadev.inews.data.model.NewsResponse;
import com.ednadev.inews.data.receiver.INNetworkChangeReceiver;
import com.ednadev.inews.di.ViewModelProviderFactory;
import com.ednadev.inews.ui.news.INNewsFragment;
import com.ednadev.inews.ui.splash.INSplashFragment;
import com.ednadev.inews.ui.splash.INSplashViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.DaggerFragment;

public class MainActivity extends DaggerAppCompatActivity {

    private INSplashViewModel splashViewModel;
    private INNetworkChangeReceiver networkChangeReceiver;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveTo(new INSplashFragment());
        initReceivers();
        initUI();
        initNews();
    }

    private void initUI() {
        splashViewModel = new ViewModelProvider(getViewModelStore(), providerFactory).get(INSplashViewModel.class);
    }

    private void initNews() {
        splashViewModel.getNews();
        splashViewModel.observeOnGetNews().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                if (!newsResponse.getArticles().isEmpty()) {
                    INNewsFragment fragment = new INNewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("news", newsResponse.getArticles());
                    fragment.setArguments(bundle);
                    moveTo(fragment);
                }
                else
                    Toast.makeText(getApplicationContext(), "Empty Articles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveTo(DaggerFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_rigth
                )
                .replace(R.id.main_container, fragment)
                .commit();
    }

    private void initReceivers() {
        networkChangeReceiver = new INNetworkChangeReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeReceiver);
    }
}