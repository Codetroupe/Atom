package com.trevonn.molecule.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Trevonn on 2018/2/8.
 */

public class WebChromeClientImpl extends WebChromeClient {

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
