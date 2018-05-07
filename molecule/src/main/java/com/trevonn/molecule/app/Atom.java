package com.trevonn.molecule.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Jeffrey on 2018/3/13
 * @function 全局类
 */

public final class Atom {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getMacaoConfigs();
    }


    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
