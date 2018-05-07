package com.trevonn.molecule.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;


import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.trevonn.molecule.delegates.web.event.Event;
import com.trevonn.molecule.delegates.web.event.EventManager;
import com.trevonn.molecule.logger.AndroidLogAdapter;
import com.trevonn.molecule.logger.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.Interceptor;

/**
 * Created by Jeffrey on 2018/3/13
 * @function 配置方法
 */


public class Configurator {

    private static final HashMap<Object,Object> MACAO_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    private Configurator(){
        MACAO_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
        MACAO_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getMacaoConfigs(){
        return MACAO_CONFIGS;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        MACAO_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
        Utils.init(Atom.getApplicationContext());
    }

    public final Configurator withApiHost(String host){
        MACAO_CONFIGS.put(ConfigKeys.API_HOST.name(),host);
        return this;
    }

    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1;i < ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        MACAO_CONFIGS.put(ConfigKeys.INTERCEPTORS,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        MACAO_CONFIGS.put(ConfigKeys.INTERCEPTORS,INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        MACAO_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        MACAO_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET,appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        MACAO_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
        return this;
    }

    public Configurator withJavascriptInterface(@NonNull String name){
        MACAO_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE,name);
        return  this;
    }

    public Configurator withWebEvnet(@NonNull String name, @NonNull Event evnet){
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name,evnet);
        return  this;
    }

    //浏览器加载的HOST
    public Configurator withWebHoast(String host){
        MACAO_CONFIGS.put(ConfigKeys.WEB_HOST,host);
        return this;
    }


    public Configurator withDebug(boolean debug){
        MACAO_CONFIGS.put(ConfigKeys.DEBUG,debug);
        return this;
    }

    public Configurator withLog(boolean log){
        MACAO_CONFIGS.put(ConfigKeys.LOGGER.name(),log);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) MACAO_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }



    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = MACAO_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) MACAO_CONFIGS.get(key);
    }

}

