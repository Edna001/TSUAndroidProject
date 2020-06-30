package com.ednadev.inews.components.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ednadev.inews.data.model.NewsModel;
import com.ednadev.inews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class INLatestNewsAdapter extends RecyclerView.Adapter<INLatestNewsAdapter.ViewHolder> {
    private ArrayList<NewsModel> latestNews;
    private OnLatestClickListener clickListener;

    public INLatestNewsAdapter(ArrayList<NewsModel> arrayListOfNews, OnLatestClickListener clickListener) {
        latestNews = arrayListOfNews;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsModel nModel = latestNews.get(position);
        holder.latestTitle.setText(nModel.getTitle());
        if (nModel.getUrlToImage() != null && !nModel.getUrlToImage().isEmpty())
            Picasso.get().load(nModel.getUrlToImage()).into(holder.latestImage);

        holder.latestReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLatestClickCallback(nModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView latestTitle;
        private ImageView latestImage;
        private Button latestReadMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            latestTitle = itemView.findViewById(R.id.latestTitle);
            latestImage = itemView.findViewById(R.id.latestImage);
            latestReadMore = itemView.findViewById(R.id.latestReadMore);
        }
    }

    public interface OnLatestClickListener {
        void onLatestClickCallback(NewsModel newsModel);
    }
}
