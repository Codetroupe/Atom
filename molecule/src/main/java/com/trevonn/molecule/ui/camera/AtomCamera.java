package com.trevonn.molecule.ui.camera;

import android.net.Uri;

import com.trevonn.molecule.delegates.PermissionCheckerDelegate;
import com.trevonn.molecule.util.file.FileUtil;


/**
 * Created by Jeffrey on 2018/3/15
 * @function 基类
 */

public class AtomCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
