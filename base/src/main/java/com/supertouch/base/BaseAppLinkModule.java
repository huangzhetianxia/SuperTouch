package com.supertouch.base;

import android.content.res.Configuration;

public abstract class BaseAppLinkModule {

        public BaseAppLinkModule(){}

        public void onCreate(){}

        public void onTerminate(){}

        public void onLowMemory(){}

        public void onTrimMemory(int level){}

        public void onConfigurationChanged(Configuration newConfig){}

}
