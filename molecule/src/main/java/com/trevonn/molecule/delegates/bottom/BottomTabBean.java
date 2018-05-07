package com.trevonn.molecule.delegates.bottom;

import android.graphics.drawable.Drawable;

/**
 * Created by Jeffrey on 2018/3/14
 * @function 底部菜单单项bean
 */


public final class BottomTabBean {

    private final Drawable ICON;
    private final CharSequence TITLE;

    public BottomTabBean(Drawable icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public Drawable getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
