package com.funny.note.mynote.app.manager;

import android.content.Context;

import com.funny.note.mynote.app.utils.SharedPreferencesUtil;

/**
 * Created by admin on 15/12/27.
 */
public class GlobalManager {

    private static GlobalManager manager;

    public static final GlobalManager getManager() {
        if (manager == null) {
            manager = new GlobalManager();
        }
        return manager;
    }

    private Context context;
    private SharedPreferencesUtil sp;


    private GlobalManager() {

    }

    public void init(Context context) {
        this.context = context;
        sp = new SharedPreferencesUtil(context);
    }


    public SharedPreferencesUtil getSharePreference() {
        return sp;
    }
}
