package com.example.lyc.transferanimation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    TextView textView;
    TextView txtInvateWX;
    LinearLayout viewContent;
    VerticalScrollTextview txtS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInvateWX = (TextView) findViewById(R.id.txtInvateWX);
        textView = (TextView) findViewById(R.id.textView);
        viewContent = (LinearLayout) findViewById(R.id.viewContent);
        txtS = (VerticalScrollTextview) findViewById(R.id.txtS);

        txtInvateWX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                if (Build.VERSION_CODES.LOLLIPOP <= BuildConfig.VERSION_CODE) {
//                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, txtInvateWX, "button1").toBundle());
//                } else {
                startActivity(intent);
//                }
            }
        });
        initVV();
    }

    void initVV() {
        ArrayList<CharSequence> lists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();

            String name = "逗比" + i;
            String d;
            if (i % 3 == 0) {
                d = "返回的数据为 JSON，返回后会对返回的数据进行一次返回的数据为 JSON，返回后会对返回的数据进行一返回的数据为 JSON，返回后会对返回的数据进行一次返回的数据为 JSON，返回后会对返回的数据进行一次次 3333333333333  " + i;
            } else if (i % 2 == 0) {
                d = "返回的数据为 JSON  \"返回的数据为 JSON  \" \"返回的数据为 JSON  \" \"返回的数据为 JSON  \" " + i;
            } else {
                d = "返回的的数据为 JSON  " + i;
            }
            sb.append(name);
            sb.append(d);
            SpannableString spannableString = new SpannableString(sb.toString());
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6060")), 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            lists.add(spannableString);
        }
        Log.i("time1", System.currentTimeMillis() + "");
        txtS.setTextParams(16, 300, Color.BLUE, false);
        txtS.setAnimTime(300);
        txtS.setTextStillTime(1000);
        initLength(lists);

    }


    void startView(ArrayList<CharSequence> lists, int[] lengths, int width) {
        ArrayList<CharSequence> datas = new ArrayList<>();
        SpannableStringBuilder spannableStringBuilder = null;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] > width) {//文案比控件宽 两行显示
                datas.add(lists.get(i));
            } else {//小于等于，即两条数据一起显示
                if (spannableStringBuilder == null) {//没有预存，初始化
                    spannableStringBuilder = new SpannableStringBuilder("");
                    spannableStringBuilder.append(lists.get(i));
                } else {
                    spannableStringBuilder.append("\n");
                    spannableStringBuilder.append(lists.get(i));
                    datas.add(spannableStringBuilder);
                    spannableStringBuilder = null;
                }
            }
        }
        if (spannableStringBuilder != null) {//最后一条数据
            datas.add(spannableStringBuilder);
        }

        Log.i("time3", System.currentTimeMillis() + "");
        txtS.setTextList(datas);
        txtS.startAutoScroll();
    }

    private void initLength(final ArrayList<CharSequence> lists) {
        final int[] lengths = new int[lists.size()];
        final int[] wigth = {0};
        for (int i = 0; i < lists.size(); i++) {
            CharSequence charSequence = lists.get(i);
            txtS.setTextSingle("");//为什么要加一句，因为预计算长度的时候如果上一次长度比这次长度长，会默认取上一次，原理是什么就不是很清楚了。
            txtS.setTextSingle(charSequence);
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            txtS.measure(w, h);
            int measuredWidth = txtS.getMeasuredWidth();
            Log.i("dddddd", measuredWidth + ":" + charSequence);
            lengths[i] = measuredWidth;
        }
        Log.i("time1.22", System.currentTimeMillis() + "");

        txtS.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                wigth[0] = txtS.getWidth();
                if (wigth[0] > 0) {
                    Log.i("dddddd", "        :" + wigth[0] + ":" + txtS.getPadding());
                    txtS.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.i("time2", System.currentTimeMillis() + "");
                    startView(lists, lengths, wigth[0] );
                }
            }
        });
    }

}
