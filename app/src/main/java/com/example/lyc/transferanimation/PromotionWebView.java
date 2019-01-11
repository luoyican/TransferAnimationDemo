package com.example.lyc.transferanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by luoyican on 2016/9/2.
 */
public class PromotionWebView extends WebView {

    public PromotionWebView(Context context) {


        super(context);
        init();
    }

    public PromotionWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PromotionWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private String d = "var v = document.getElementsByTagName(\"A\") || [];" +
            "    for(var i = 0;i<v.length;i++)" +
            "    {" +
            "        (function(i) {" +
            "            v[i].addEventListener('click', function() {" +
            "            var r = v[i].getAttribute('href') || \"\";" +
            "            v[i].setAttribute('href', 'javascript:void(0);');" +
            "            window.location.href = 'scheme:hostname?parms='+encodeURIComponent(r);" +
            "            })" +
            "        })(i)" +
            "    }";
    private void init() {
        if (!isInEditMode()) {
            WebSettings webSettings = getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            android.webkit.CookieManager.getInstance().setAcceptCookie(true);
        }
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("ddddddddddd", "onPageFinished:" + url);
                view.loadUrl("javascript:" + d);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("ddddddddddd", "shouldOverrideUrlLoading:" + url);
                return false;
            }

        });
    }
}