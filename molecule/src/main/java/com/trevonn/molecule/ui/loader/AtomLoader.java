package com.trevonn.molecule.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.trevonn.molecule.R;
import com.trevonn.molecule.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 2018/3/13
 * @function loader调用
 */


public class AtomLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator.name();



    private static void showLoading(Context context,String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWith = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow != null){
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWith / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height+deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 默认样式
     * @param context
     */
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    /**
     * 传入样式
     * @param context
     * @param type
     */
    public static void showLoading(Context context, Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    //停止loading
    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if(dialog!=null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }

}