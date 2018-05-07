package com.trevonn.molecule.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.trevonn.molecule.delegates.web.event.Event;
import com.trevonn.molecule.delegates.web.event.EventManager;
import com.trevonn.molecule.util.log.AtomLogger;


/**
 * Created by Jeffrey on 2018/4/3
 */

public class AtomWebInterface { private final WebDelegate DELEGATE;

    private AtomWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static AtomWebInterface create(WebDelegate delegate) {
        return new AtomWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        AtomLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}