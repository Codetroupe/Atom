package com.trevonn.molecule.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trevonn.molecule.app.Atom;


/**
 * Created by Jeffrey on 2018/3/13
 * @function 内部存储
 */


public final class AtomPreference {
    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Atom.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    /**
     * 设置应用程序配置文件
     * @param val
     */
    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    /**
     * 获取应用程序配置文件
     * @return
     */
    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    /**
     * 获取应用程序配置文件JSONObject
     * @return
     */
    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    /**
     * 移出内部存储文件
     */
    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    /**
     * 清空内部存储
     */
    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    /**
     * 存状态值
     * @param key
     * @param flag
     */
    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    /**
     * 取状态值
     * @param key
     * @return
     */
    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    /**
     * 存值
     * @param key
     * @param val
     */
    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }



}
