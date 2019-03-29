package com.weerjean.testdemo.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;

/**
 * Created by : weiwenjie986 on 18/11/1 下午4:01. Description :
 */
public class BaseWebview extends WebView {

    public BaseWebview(Context context) {
        super(context);
        initWebivew();
    }

    public BaseWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebivew();
    }

    public BaseWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebivew();
    }


    private void initWebivew() {
        WebSettings webSettings = getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setRenderPriority(RenderPriority.HIGH);
        webSettings.setSupportZoom(false);
        webSettings.setDomStorageEnabled(true);
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSaveFormData(true);
        webSettings.setAppCacheMaxSize(5120L);
        String appCacheDir = this.getContext().getDir("cache", 0).getPath() + "/ff_webcache";
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(appCacheDir);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString());

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }

        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");

    }



}
