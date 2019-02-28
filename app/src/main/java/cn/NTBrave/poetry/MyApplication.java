package cn.NTBrave.poetry;

import android.app.Application;
import android.content.Context;


import com.mob.MobSDK;

import org.litepal.LitePal;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

//全局Context
public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        //注册Mob，初始化MobSDK
        MobSDK.init(this);
        //注册和风天气
        HeConfig.init("HE1808181021011344","c6a58c3230694b64b78facdebd7720fb");
        HeConfig.switchToFreeServerNode();
    }

    public static Context getContext() {
        return context;
    }


}
