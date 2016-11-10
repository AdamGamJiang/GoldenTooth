package com.gan.goldentooth.goldentooth.base;

import android.app.Application;

/**
 * Created by issuser on 2016/11/10.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
