package com.ednadev.inews.di;

import com.ednadev.inews.data.rest.NewsService;
import com.ednadev.inews.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    Retrofit provideNewsRetrofit() {
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    NewsService provideNewsService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }
}
