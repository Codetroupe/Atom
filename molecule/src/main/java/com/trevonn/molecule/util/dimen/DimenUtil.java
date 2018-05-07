package com.trevonn.molecule.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.trevonn.molecule.app.Atom;


/**
 * Created by Jeffrey on 2018/3/13
 * @function 获取屏幕尺寸
 */


public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Atom.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Atom.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
