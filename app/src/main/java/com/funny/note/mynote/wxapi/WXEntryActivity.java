package com.funny.note.mynote.wxapi;

import android.os.Bundle;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_LONG).show();
    }

    SocializeListeners.SnsPostListener mSnsPostListener = new SocializeListeners.SnsPostListener() {

        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int stCode,
                               SocializeEntity entity) {
            if (stCode == 200) {
                Toast.makeText(WXEntryActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WXEntryActivity.this, "分享失败 : error code : " + stCode, Toast.LENGTH_SHORT).show();
            }
        }
    };
}