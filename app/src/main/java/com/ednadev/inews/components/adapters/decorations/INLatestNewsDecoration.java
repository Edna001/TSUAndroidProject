package com.ednadev.inews.components.adapters.decorations;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class INLatestNewsDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        float density = parent.getContext().getResources().getDisplayMetrics().density;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();

        layoutParams.width = parent.getMeasuredWidth() - ((int)(15 * density) + (int)(10 * density) + 72);

        if (parent.getChildAdapterPosition(view) == 0) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin =
                    (parent.getMeasuredWidth() - view.getLayoutParams().width) / 2;
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = (int)(5 * density);
        } else if (parent.getChildAdapterPosition(view) == (state.getItemCount() - 1)) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin =
                    (parent.getMeasuredWidth() - view.getLayoutParams().width) / 2;
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = (int)(5 * density);
        } else {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin = (int)(5 * density);
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin = (int)(5 * density);
        }
    }
}
