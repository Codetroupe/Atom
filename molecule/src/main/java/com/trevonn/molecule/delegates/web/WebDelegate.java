package com.trevonn.molecule.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;


import com.trevonn.molecule.app.Atom;
import com.trevonn.molecule.app.ConfigKeys;
import com.trevonn.molecule.delegates.AtomDelegate;
import com.trevonn.molecule.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Jeffrey on 2018/4/3
 */

public abstract class WebDelegate extends AtomDelegate implements IWebViewInitializer{

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = false;
    private AtomDelegate mTopDelegate = null;
    public WebDelegate(){

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }


    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebView(){
        if(mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setInitializer();
            if(initializer !=null){
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Atom.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(AtomWebInterface.create(this),name);
                mIsWebViewAbailable = true;
            }else{
                throw new NullPointerException("Initializer is null!");
            }
        }
    }
    public void setTopDelegate(AtomDelegate delegate) {
        mTopDelegate = delegate;
    }

    public AtomDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }



    public WebView getWebView(){
        if(mWebView == null){
            throw new NullPointerException("WebView is null!");
        }
        return mIsWebViewAbailable ? mWebView:null;
    }

    public String getUrl(){
        if(mUrl == null){
            throw new NullPointerException("Url is null!");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mWebView != null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mWebView !=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWebView !=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
