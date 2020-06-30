package com.ednadev.inews.ui.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ednadev.inews.R;
import com.ednadev.inews.components.adapters.INLatestNewsAdapter;
import com.ednadev.inews.components.adapters.decorations.INLatestNewsDecoration;
import com.ednadev.inews.components.dialogs.INAlertDialogView;
import com.ednadev.inews.data.model.NewsModel;
import com.ednadev.inews.ui.cartegory.INNewsCategoryFragment;
import com.ednadev.inews.ui.item.INNewsItemFragment;
import com.ednadev.inews.utils.NewsCategoryEnum;

import java.util.ArrayList;

import dagger.android.support.DaggerFragment;

public class INNewsFragment extends DaggerFragment implements INLatestNewsAdapter.OnLatestClickListener {

    private RecyclerView latestNews;
    private TextView business;
    private TextView entertainment;
    private TextView general;
    private TextView health;
    private TextView science;
    private TextView sports;
    private TextView technology;
    private FrameLayout newsAdd;
    private FrameLayout newsVideos;
    private FrameLayout newsSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        initLatestNews();
    }

    private void initUI(View view) {
        latestNews = view.findViewById(R.id.newsLatestNews);
        business = view.findViewById(R.id.newsBusiness);
        entertainment = view.findViewById(R.id.newsEntertainment);
        general = view.findViewById(R.id.newsGeneral);
        health = view.findViewById(R.id.newsHealth);
        science = view.findViewById(R.id.newsScience);
        sports = view.findViewById(R.id.newsSports);
        technology = view.findViewById(R.id.newsTechnology);
        newsAdd = view.findViewById(R.id.newsAdd);
        newsVideos = view.findViewById(R.id.newsVideos);
        newsSearch = view.findViewById(R.id.newsSearch);

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.BUSINESS);
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.ENTERTAINMENT);
            }
        });

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.GENERAL);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.HEALTH);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.SCIENCE);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.SPORTS);
            }
        });

        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTo(new INNewsCategoryFragment(), NewsCategoryEnum.TECHNOLOGY);
            }
        });

        newsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastDialog();
            }
        });

        newsVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastDialog();
            }
        });

        newsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastDialog();
            }
        });
    }

    private void initLatestNews() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            ArrayList<NewsModel> arrayOfNews = bundle.getParcelableArrayList("news");

            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(latestNews);

            latestNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            latestNews.addItemDecoration(new INLatestNewsDecoration());
            latestNews.setAdapter(new INLatestNewsAdapter(arrayOfNews, this));
        }
    }

    @Override
    public void onLatestClickCallback(NewsModel newsModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("news", newsModel);
        INNewsItemFragment fragment = new INNewsItemFragment();
        fragment.setArguments(bundle);

        nextTo(fragment, null);
    }

    private void toastDialog() {
        INAlertDialogView alertDialog = new INAlertDialogView(getContext());
        alertDialog.setMessage(getString(R.string.coming_soon));
        alertDialog.setToastStyle();
        alertDialog.show();
    }

    private void nextTo(DaggerFragment fragment,@Nullable NewsCategoryEnum categoryEnum) {

        if (categoryEnum != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("category", categoryEnum);
            fragment.setArguments(bundle);
        }

        getFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_up,
                        R.anim.slide_in_bottom,
                        R.anim.slide_in_up,
                        R.anim.slide_in_bottom
                )
                .addToBackStack(null)
                .replace(R.id.newsContainer, fragment)
                .commit();
    }
}