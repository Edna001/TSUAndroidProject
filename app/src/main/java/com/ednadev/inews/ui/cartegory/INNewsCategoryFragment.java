package com.ednadev.inews.ui.cartegory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ednadev.inews.R;
import com.ednadev.inews.components.adapters.INCategoryNewsAdapter;
import com.ednadev.inews.data.model.NewsModel;
import com.ednadev.inews.data.model.NewsResponse;
import com.ednadev.inews.di.ViewModelProviderFactory;
import com.ednadev.inews.ui.item.INNewsItemFragment;
import com.ednadev.inews.ui.splash.INSplashViewModel;
import com.ednadev.inews.utils.NewsCategoryEnum;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class INNewsCategoryFragment extends DaggerFragment implements INCategoryNewsAdapter.OnCategoryClickListener {

    private RecyclerView categoryNews;
    private TextView categoryTitle;
    private INNewsCategoryViewModel categoryViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_news_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initCategoryNews();
    }

    private void initUI(View view) {
        categoryViewModel = new ViewModelProvider(getViewModelStore(), providerFactory).get(INNewsCategoryViewModel.class);
        categoryNews = view.findViewById(R.id.categoryNews);
        categoryTitle = view.findViewById(R.id.categoryTitle);
    }

    private void initCategoryNews() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            NewsCategoryEnum categoryEnum = (NewsCategoryEnum) bundle.getSerializable("category");

            categoryTitle.setText(categoryEnum.getValue());

            categoryViewModel.getCategoryNews(categoryEnum.getValue());
            categoryViewModel.observeOnGetCategoryNews().observe(this, new Observer<NewsResponse>() {
                @Override
                public void onChanged(NewsResponse newsResponse) {
                    if (!newsResponse.getArticles().isEmpty()) {
                        categoryNews.setLayoutManager(new LinearLayoutManager(getContext()));
                        categoryNews.setAdapter(new INCategoryNewsAdapter(newsResponse.getArticles(), INNewsCategoryFragment.this));
                    }
                }
            });
        }
    }

    @Override
    public void onCategoryClickCallback(NewsModel newsModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("news", newsModel);
        INNewsItemFragment fragment = new INNewsItemFragment();
        fragment.setArguments(bundle);
        nextTo(fragment);
    }

    private void nextTo(DaggerFragment fragment) {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_up,
                        R.anim.slide_in_bottom,
                        R.anim.slide_in_up,
                        R.anim.slide_in_bottom
                )
                .addToBackStack(null)
                .replace(R.id.categoryContainer, fragment)
                .commit();
    }
}