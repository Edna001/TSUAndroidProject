package com.ednadev.inews.components.dialogs;

import android.view.animation.Interpolator;

public class INBounceInterpolator implements Interpolator {
    private double mAmplitude = 0.3;
    private double mFrequency = 10;

    public INBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public INBounceInterpolator() {
    }

    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1);
    }
}
