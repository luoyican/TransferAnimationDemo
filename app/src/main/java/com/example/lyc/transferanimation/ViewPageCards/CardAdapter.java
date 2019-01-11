package com.example.lyc.transferanimation.ViewPageCards;

import android.support.v7.widget.CardView;

public interface CardAdapter {
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}

