package com.ednadev.inews.ui.splash;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.ednadev.inews.data.model.NewsResponse;
import com.ednadev.inews.data.repository.NewsRepository;
import com.ednadev.inews.utils.StateResource;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class INSplashViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MediatorLiveData<NewsResponse> onGetNews = new MediatorLiveData<>();

    @Inject
    public INSplashViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void getNews() {
        newsRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { disposable.add(d); }
                    @Override
                    public void onNext(NewsResponse newsResponse) { onGetNews.setValue(newsResponse); }
                    @Override
                    public void onError(Throwable e) { Log.d("INSplashViewModel", " onError: " + e.getMessage()); }
                    @Override
                    public void onComplete() { }
                });
    }
    public LiveData<NewsResponse> observeOnGetNews() { return onGetNews; }
}
