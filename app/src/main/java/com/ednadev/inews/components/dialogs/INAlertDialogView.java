package com.ednadev.inews.components.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.ednadev.inews.R;

import java.util.concurrent.TimeUnit;

public class INAlertDialogView extends Dialog implements View.OnClickListener {

    private LinearLayout alertPositiveButton;
    private CardView alertMainView;
    private TextView alertPositiveText;
    private TextView alertMessage;
    private RelativeLayout buttonContainer;
    private View toastStyleEmptyView;
    private ImageView alertTitleImageView;
    private TextView alertTitleText;

    OnAlertPositiveButtonListener positiveButtonListener;

    public INAlertDialogView(@NonNull Context context) {
        super(context, R.style.INDialogTheme);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.alert_dialog_view);

        alertPositiveButton = findViewById(R.id.alertPositiveButton);
        alertMainView = findViewById(R.id.alertMainView);
        alertPositiveText = findViewById(R.id.alertPositiveText);
        alertMessage = findViewById(R.id.alertMessage);
        buttonContainer = findViewById(R.id.button_container);
        toastStyleEmptyView = findViewById(R.id.toastStyleEmptyView);
        alertTitleImageView = findViewById(R.id.alertTitleImageView);
        alertTitleText = findViewById(R.id.alertTitleText);

        alertTitleImageView.setVisibility(View.GONE);
        alertPositiveButton.setOnClickListener(this);

        Animation animate = AnimationUtils.loadAnimation(context, R.anim.bounce);
        INBounceInterpolator interpolator = new INBounceInterpolator(0.3, 10.0);
        animate.setInterpolator(interpolator);
        alertMainView.startAnimation(animate);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == alertPositiveButton.getId()) {
            if (positiveButtonListener != null)
                positiveButtonListener.onPressesPositiveButton(this);
            else
                dismiss();
        }
    }

    public void setPositiveButtonListener(OnAlertPositiveButtonListener positiveButtonListener) {
        this.positiveButtonListener = positiveButtonListener;
        alertPositiveButton.setVisibility(View.VISIBLE);
    }

    public void initPositiveButton(String buttonText) {
        alertPositiveText.setText(buttonText);
        alertPositiveButton.setVisibility(View.VISIBLE);
    }

    public void initPositiveButton(String buttonText, OnAlertPositiveButtonListener positiveButtonListener) {
        alertPositiveText.setText(buttonText);
        this.setPositiveButtonListener(positiveButtonListener);
    }

    public void setMessage(String messageText) {
        alertMessage.setText(messageText);
    }

    public void setAlignment() {
        alertMessage.setGravity(Gravity.START);
    }

    public void setMessage(String messageText, @ColorRes int colorResId) {
        alertMessage.setTextColor(ContextCompat.getColor(getContext(), colorResId));
        alertMessage.setText(messageText);
    }

    public void setToastStyle() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        buttonContainer.setVisibility(View.GONE);
        alertTitleText.setVisibility(View.GONE);
        alertTitleImageView.setVisibility(View.GONE);
        toastStyleEmptyView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                INAlertDialogView.this.dismiss();
            }
        }, timeUnit.toMillis(2000));
    }

    public void setTitleIcon(int resId, int colorResId, boolean disableTint) {
        if (resId == -1) {
            alertTitleImageView.setVisibility(View.GONE);
        }

        if (!disableTint) {
            alertTitleImageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        }

        if (colorResId != -1)
            alertTitleImageView.setColorFilter(ContextCompat.getColor(getContext(), colorResId));

        alertTitleImageView.setVisibility(View.VISIBLE);
        alertTitleImageView.setImageResource(resId);
    }

    public void setTitleText(String titleText) {
        alertTitleText.setText(titleText);
    }

    interface OnAlertPositiveButtonListener {
        void onPressesPositiveButton(INAlertDialogView dialog);
    }
}
