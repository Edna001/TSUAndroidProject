package com.ednadev.inews.di.main;

import com.ednadev.inews.ui.cartegory.INNewsCategoryFragment;
import com.ednadev.inews.ui.item.INNewsItemFragment;
import com.ednadev.inews.ui.news.INNewsFragment;
import com.ednadev.inews.ui.splash.INSplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract INSplashFragment contributeSplashFragment();

    @ContributesAndroidInjector()
    abstract INNewsFragment contributeNewsFragment();

    @ContributesAndroidInjector()
    abstract INNewsCategoryFragment contributeCategoryFragment();

    @ContributesAndroidInjector()
    abstract INNewsItemFragment contributeNewsItemFragment();
}
