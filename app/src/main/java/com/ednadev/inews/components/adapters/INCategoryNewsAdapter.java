package com.ednadev.inews.components.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ednadev.inews.R;
import com.ednadev.inews.data.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class INCategoryNewsAdapter extends RecyclerView.Adapter<INCategoryNewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> categoryNews;
    private OnCategoryClickListener categoryClickListener;

    public INCategoryNewsAdapter(ArrayList<NewsModel> arrayListOfNews, OnCategoryClickListener categoryClickListener) {
        categoryNews = arrayListOfNews;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final NewsModel nModel = categoryNews.get(position);
        holder.categoryTitle.setText(nModel.getTitle());
        if (nModel.getUrlToImage() != null && !nModel.getUrlToImage().isEmpty())
            Picasso.get().load(nModel.getUrlToImage()).into(holder.categoryImage);

        holder.categoryReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onCategoryClickCallback(nModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryNews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryTitle;
        private ImageView categoryImage;
        private Button categoryReadMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryItemTitle);
            categoryImage = itemView.findViewById(R.id.categoryItemImage);
            categoryReadMore = itemView.findViewById(R.id.categoryItemReadMore);
        }
    }

    public interface OnCategoryClickListener {
        void onCategoryClickCallback(NewsModel newsModel);
    }
}