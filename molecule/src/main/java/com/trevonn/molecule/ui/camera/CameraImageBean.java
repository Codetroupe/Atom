package com.trevonn.molecule.ui.camera;

import android.net.Uri;

/**
 * Created by Jeffrey on 2018/3/15
 * @function 相机bean
 */


public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}