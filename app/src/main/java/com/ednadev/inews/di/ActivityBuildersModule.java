package com.ednadev.inews.di;

import com.ednadev.inews.di.main.MainFragmentBuildersModule;
import com.ednadev.inews.di.main.MainModule;
import com.ednadev.inews.di.main.MainViewModelModule;
import com.ednadev.inews.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {
            MainModule.class,
            MainViewModelModule.class,
            MainFragmentBuildersModule.class
    })
    abstract MainActivity contributeMainActivity();
}
