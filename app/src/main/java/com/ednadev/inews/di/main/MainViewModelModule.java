package com.ednadev.inews.di.main;

import androidx.lifecycle.ViewModel;

import com.ednadev.inews.components.adapters.INCategoryNewsAdapter;
import com.ednadev.inews.di.ViewModelKey;
import com.ednadev.inews.ui.cartegory.INNewsCategoryViewModel;
import com.ednadev.inews.ui.splash.INSplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(INSplashViewModel.class)
    abstract ViewModel bindSplashViewModel(INSplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(INNewsCategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(INNewsCategoryViewModel viewModel);
}
