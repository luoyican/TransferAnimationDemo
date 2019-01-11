package com.example.lyc.transferanimation.ViewPageCards;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lyc.transferanimation.R;
import com.example.lyc.transferanimation.ScaleImageView;

import java.util.ArrayList;
import java.util.List;

public class CardPageAdapter extends PagerAdapter implements CardAdapter {
    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;

    public CardPageAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.card_view_item, container, false);
        container.addView(view);
        bindViews(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(20);//设置阴影高度px
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bindViews(CardItem item, View view) {
        RelativeLayout viewContent = (RelativeLayout) view.findViewById(R.id.viewContent);
        ScaleImageView ivBg = (ScaleImageView) view.findViewById(R.id.ivBg);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        TextView txtTip = (TextView) view.findViewById(R.id.txtTip);
        ImageView viewCode = (ImageView) view.findViewById(R.id.viewCode);

        txtName.setText(item.getTxtName());
        txtTitle.setText(item.getTxtTitle());
        txtTip.setText(item.getTxtTip());

    }
}
