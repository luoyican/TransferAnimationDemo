package com.example.lyc.transferanimation;

/**
 * Created by luoyican on 2017/4/12.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;


public class VerticalScrollTextview extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private static final int FLAG_START_AUTO_SCROLL = 0;
    private static final int FLAG_STOP_AUTO_SCROLL = 1;

    private float mTextSize = 14;
    private int mPadding = 5;
    private int textColor = Color.rgb(0x64, 0x64, 0x64);
    private boolean isSingleLine = false;

    /**
     * @param textSize     字号
     * @param padding      内边距
     * @param textColor    字体颜色
     * @param isSingleLine 是否单行
     */
    public void setTextParams(float textSize, int padding, int textColor, boolean isSingleLine) {
        mTextSize = textSize;
        mPadding = padding;
        this.textColor = textColor;
        this.isSingleLine = isSingleLine;
    }

    private OnItemClickListener itemClickListener;
    private Context mContext;
    private int currentId = -1;
    private ArrayList<CharSequence> textList;
    private Handler handler;

    public VerticalScrollTextview(Context context) {
        this(context, null);
        mContext = context;
    }

    public VerticalScrollTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        textList = new ArrayList<>();
    }

    public void setAnimTime(long animDuration) {
        setFactory(this);
        Animation in = new TranslateAnimation(0, 0, animDuration, 0);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, -animDuration);
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        setInAnimation(in);
        setOutAnimation(out);
    }

    /**
     * 间隔时间
     *
     * @param time
     */
    public void setTextStillTime(final long time) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_START_AUTO_SCROLL:
                        if (textList.size() > 0) {
                            currentId = (currentId + 1) % textList.size();
                            setText(textList.get(currentId));

                        }
                        handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL, time);
                        break;
                    case FLAG_STOP_AUTO_SCROLL:
                        handler.removeMessages(FLAG_START_AUTO_SCROLL);
                        break;
                }
            }
        };
    }

    /**
     */
    public void setTextSingle(CharSequence charSequence) {
        TextView t = (TextView) getNextView();
        setText(charSequence);
    }

    /**
     * 设置数据源
     *
     * @param titles
     */
    public void setTextList(ArrayList<CharSequence> titles) {
        textList.clear();
        textList.addAll(titles);
        currentId = -1;
    }

    /**
     * 开始滚动
     */
    public void startAutoScroll() {
        if (handler != null)
            handler.sendEmptyMessage(FLAG_START_AUTO_SCROLL);
    }

    /**
     * 停止滚动
     */
    public void stopAutoScroll() {
        if (handler != null)
            handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
    }

    public int getPadding() {
        return mPadding;
    }

    @Override
    public View makeView() {
        TextView t = new TextView(mContext);
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        t.setMaxLines(2);
        t.setPadding(mPadding, mPadding, mPadding, mPadding);
        t.setTextColor(textColor);
        t.setTextSize(TypedValue.COMPLEX_UNIT_PX, dip2px(mTextSize));
        if (isSingleLine) t.setSingleLine();
        t.setEllipsize(TextUtils.TruncateAt.END);
        t.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));

        t.setClickable(true);
        t.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
                    itemClickListener.onItemClick(currentId);
                }
            }
        });
        return t;
    }

    private float dip2px(float dipValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, getContext().getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * 设置点击事件监听
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 轮播文本点击监听器
     */
    public interface OnItemClickListener {
        /**
         * 点击回调
         *
         * @param position 当前点击ID
         */
        void onItemClick(int position);
    }

}