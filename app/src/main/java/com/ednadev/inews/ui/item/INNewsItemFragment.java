package com.ednadev.inews.ui.item;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ednadev.inews.R;
import com.ednadev.inews.data.model.NewsModel;
import com.squareup.picasso.Picasso;

import dagger.android.support.DaggerFragment;

public class INNewsItemFragment extends DaggerFragment {

    ImageView newsItemImage;
    TextView newsItemTitle;
    TextView newsItemContent;
    TextView newsItemSource;
    ImageButton newsItemShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_news_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI(view);

    }

    private void initUI(View view) {
        newsItemImage = view.findViewById(R.id.newsItemImage);
        newsItemTitle = view.findViewById(R.id.newsItemTitle);
        newsItemContent = view.findViewById(R.id.newsItemContent);
        newsItemSource = view.findViewById(R.id.newsItemSource);
        newsItemShare = view.findViewById(R.id.newsItemShare);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            final NewsModel newsModel = bundle.getParcelable("news");

            Picasso.get().load(newsModel.getUrlToImage()).into(newsItemImage);
            newsItemTitle.setText(newsModel.getTitle());
            newsItemSource.setText(newsModel.getSource().getName());

            if (newsModel.getContent() == null)
                newsItemContent.setText(newsModel.getDescription());
            else
                newsItemContent.setText(newsModel.getContent());

            newsItemShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/*");
                    intent.putExtra(Intent.EXTRA_TEXT, newsModel.getUrl());

                    Intent shareIntent = Intent.createChooser(intent, "Share this news...");
                    startActivity(shareIntent);
                }
            });
        }
    }
}