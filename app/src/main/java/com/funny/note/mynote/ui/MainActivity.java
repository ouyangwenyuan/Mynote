package com.funny.note.mynote.ui;

import android.content.Intent;

import com.funny.note.mynote.R;

/**
 * Created by admin on 15/12/26.
 */
public class MainActivity extends BaseActivity {
    @Override
    public void initView() {
//        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, LoginActivity.class));
    }
}
