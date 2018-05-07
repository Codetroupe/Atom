package com.trevonn.molecule.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.trevonn.molecule.app.Atom;
import com.trevonn.molecule.app.ConfigKeys;
import com.trevonn.molecule.delegates.IPageLoadListener;
import com.trevonn.molecule.delegates.web.WebDelegate;
import com.trevonn.molecule.delegates.web.route.Router;
import com.trevonn.molecule.ui.loader.AtomLoader;
import com.trevonn.molecule.util.log.AtomLogger;
import com.trevonn.molecule.util.storage.AtomPreference;


/**
 * Created by Trevonn on 2018/2/8.
 */

public class WebViewClientImpl extends WebViewClient {


    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Atom.getHandler();


    public void setPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate){
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        AtomLogger.d("shouldOverrideUrlLoading",url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mIPageLoadListener !=null){
            mIPageLoadListener.inLoadStart();
        }
        AtomLoader.showLoading(view.getContext());
    }

    //获取浏览器Cookie
    private void syncCookie(){
        final CookieManager manager = CookieManager.getInstance();
        /**
         * 注意，这里的Cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = Atom.getConfiguration(ConfigKeys.WEB_HOST);
        if(webHost != null){
            if(manager.hasCookies()){
                final String cookieStr = manager.getCookie(webHost);
                if(cookieStr !=null && !cookieStr.equals("")){
                    AtomPreference.addCustomAppProfile("cookie",cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if(mIPageLoadListener !=null){
            mIPageLoadListener.onLoadEnd();
        }

        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                AtomLoader.stopLoading();
            }
        },1000);
    }
}
