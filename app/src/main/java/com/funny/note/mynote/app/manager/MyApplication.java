package com.funny.note.mynote.app.manager;

import android.app.Application;

/**
 * Created by admin on 15/12/27.
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        GlobalManager.getManager().init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
