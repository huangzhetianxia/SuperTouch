package com.supertouch.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;

/**
 * base module application
 */
public class BaseApplication extends Application {

    public static Context context;
    public static Activity mContext;

    private String[] appClazz = null;
    private ArrayList<BaseAppLinkModule> applicationList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                mContext = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mContext = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        initOtherModuleApplication();
    }

    private void initOtherModuleApplication() {
        if (null != appClazz) {
            for (String className : appClazz) {
                try {
                    Class<?> clazz = Class.forName(className);
                    try {
                        BaseAppLinkModule baseAppLinkModule = (BaseAppLinkModule) clazz.newInstance();
                        baseAppLinkModule.onCreate();
                        applicationList.add(baseAppLinkModule);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return;
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseAppLinkModule baseAppLinkModule : applicationList) {
            baseAppLinkModule.onTerminate();
        }
    }

    // 获取顶层的Activity
    public static Activity getTopActivity() {
        return mContext;
    }

}
