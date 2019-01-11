package com.example.lyc.transferanimation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lyc.transferanimation.ViewPageCards.CardItem;
import com.example.lyc.transferanimation.ViewPageCards.CardPageAdapter;
import com.example.lyc.transferanimation.ViewPageCards.ShadowTransformer;

public class SecondActivity extends Activity {
    private ViewPager mViewPager;
    private TextView txtInvateWX;
    private ImageView ivTT;

    private CardPageAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        txtInvateWX = (TextView) findViewById(R.id.txtInvateWX);
        ivTT = (ImageView) findViewById(R.id.ivTT);


        init();
        updateUI();

    }

    private void init() {
        txtInvateWX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView cardview = mCardAdapter.getCardViewAt(mViewPager.getCurrentItem());
                View view = cardview.findViewById(R.id.viewContent);
                view.setDrawingCacheEnabled(true);
                Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);
                ivTT.setImageBitmap(bitmap);
            }
        });
    }

    /**
     * 生成bitmap
     */
    private Bitmap productBitmap(View view) {
        Bitmap shareBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(shareBitmap);
        view.draw(c);
        return shareBitmap;
    }

    private void updateUI() {
        mCardAdapter = new CardPageAdapter();
        mCardAdapter.addCardItem(new CardItem.Builder().setTxtName("一本正经").setTxtTitle("邀你一起赚外快").setTxtTip("长按识别二维码 0门槛注册经纪人").build());
        mCardAdapter.addCardItem(new CardItem.Builder().build());
        mCardAdapter.addCardItem(new CardItem.Builder().setTxtName("111111").setTxtTitle("34344323443433423432").build());
        mCardAdapter.addCardItem(new CardItem.Builder().setTxtTip("gagagagagagagagaa4").build());

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        mCardShadowTransformer.setTransparentEnable(true);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);//加阴影跟动画效果
        mViewPager.setOffscreenPageLimit(3);//设置一页显示最大个数
        mViewPager.setPageMargin(30);//设置item间距
    }
}
