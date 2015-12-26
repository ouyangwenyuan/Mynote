package com.funny.note.mynote.ui.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.TencentWbShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

import java.util.Map;

/**
 * Created by admin on 15/9/10.
 */
public class ThirdPartyHandler {

    private static final String DESCRIPTOR = "com.umeng.share";
    public final UMSocialService mController = UMServiceFactory.getUMSocialService(DESCRIPTOR);
    private static String TAG = ThirdPartyHandler.class.getSimpleName();
    private Activity activity;

    public interface OnThirdPartyLoginFinish {
        void onLoginSuccessful(String sign, String userName, String platform, String openid, String avatarUrl);

        void onLoginFail(String error);
    }

    public ThirdPartyHandler(Activity activity) {
        this.activity = activity;

        addQZoneQQPlatform();
        addWeixinPlatform();
        addWeiboPlatform();
    }

    private OnThirdPartyLoginFinish callback;

    public void setCallback(OnThirdPartyLoginFinish callback) {
        this.callback = callback;
    }

    private void addQZoneQQPlatform() {
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, MyConfig.qq_appId, MyConfig.qq_appKey);
        qqSsoHandler.setTargetUrl("http://www.evolvingera.com.cn");
        qqSsoHandler.addToSocialSDK();
        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, MyConfig.qq_appId, MyConfig.qq_appKey);
        qZoneSsoHandler.addToSocialSDK();
    }

    private void addWeixinPlatform() {
        // 配置SSO
        UMWXHandler circleHandler = new UMWXHandler(activity, MyConfig.weixin_appId, MyConfig.weixin_appKey);
        circleHandler.setTargetUrl("http://www.evolvingera.com.cn");
        circleHandler.setToCircle(true);
        circleHandler.addToSocialSDK();

        UMWXHandler weixinHandler = new UMWXHandler(activity, MyConfig.weixin_appId, MyConfig.weixin_appKey);
        weixinHandler.setTargetUrl("http://www.evolvingera.com.cn");
        weixinHandler.setToCircle(false);
        weixinHandler.addToSocialSDK();
    }


    private void addWeiboPlatform() {
        // 添加新浪SSO授权
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        mController.getConfig().setSinaCallbackUrl("https://api.weibo.com/oauth2/default.html");
        //mController.getConfig().setSinaCallbackUrl("http://sns.whalecloud.com/sina2/callback");
        // 添加腾讯微博SSO授权
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());
    }

    public void performShare(SHARE_MEDIA platform) {
        mController.postShare(activity, platform, new SocializeListeners.SnsPostListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
//                String showText = platform.toString();
//                if (eCode == StatusCode.ST_CODE_SUCCESSED) {
//                    showText += "平台分享成功";
//                } else {
//                    showText += "平台分享失败";
//                }
//                Toast.makeText(KnowledgeDetailActivity.this, showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    public void setShareContent(String title, String content, String imageUrl, String tagUrl, String appWebSite) {


        // UMImage localImage = new UMImage(this, R.drawable.ic_launcher);
        // UMImage resImage = new UMImage(this(), R.drawable.icon);

        // 视频分享
//        UMVideo video = new UMVideo("http://v.youku.com/v_show/id_XNTc0ODM4OTM2.html");
//        // vedio.setThumb("http://www.umeng.com/images/pic/home/social/img-1.png");
//        video.setTitle(title);
//        video.setThumb(localImage);
//
//        UMusic uMusic = new UMusic( "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
//        uMusic.setAuthor("umeng");
//        uMusic.setTitle("天籁之音");
//        // uMusic.setThumb(urlImage);
//        uMusic.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");

        // UMEmoji emoji = new UMEmoji(this(),
        // "http://www.pc6.com/uploadimages/2010214917283624.gif");
        // UMEmoji emoji = new UMEmoji(this(),
        // "/storage/sdcard0/emoji.gif");

        UMImage localImage = new UMImage(activity, imageUrl);

        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setShareContent(content);
        weixinContent.setTitle(title);
        weixinContent.setTargetUrl(tagUrl);
        weixinContent.setShareMedia(localImage);
        weixinContent.setAppWebSite(appWebSite);
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent(content);
        circleMedia.setTitle(title);
        circleMedia.setShareMedia(localImage);
        // circleMedia.setShareMedia(uMusic);
        // circleMedia.setShareMedia(video);
        circleMedia.setTargetUrl(tagUrl);
        circleMedia.setAppWebSite(appWebSite);
        mController.setShareMedia(circleMedia);


//        UMImage qzoneImage = new UMImage(this, "http://www.umeng.com/images/pic/social/integrated_3.png");
//        qzoneImage.setTargetUrl(tagUrl);

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent(content);
        qzone.setTargetUrl(tagUrl);
        qzone.setTitle(title);
        qzone.setShareMedia(localImage);
        // qzone.setShareMedia(uMusic);
        qzone.setAppWebSite(appWebSite);
        mController.setShareMedia(qzone);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent(content);
        qqShareContent.setTitle(title);
        qqShareContent.setShareMedia(localImage);
        qqShareContent.setTargetUrl(tagUrl);
        qqShareContent.setAppWebSite(appWebSite);
        mController.setShareMedia(qqShareContent);

        // 设置tencent分享内容
        TencentWbShareContent tencent = new TencentWbShareContent();
        tencent.setShareContent(content);
        tencent.setTitle(title);
        tencent.setShareMedia(localImage);
        tencent.setTargetUrl(tagUrl);
        tencent.setAppWebSite(appWebSite);
        mController.setShareMedia(tencent);

        SinaShareContent sinaContent = new SinaShareContent();
        sinaContent.setShareContent("#" + title + "#" + tagUrl);
        sinaContent.setTitle(title);
        sinaContent.setShareMedia(localImage);
        sinaContent.setTargetUrl(tagUrl);
        sinaContent.setAppWebSite(appWebSite);
        mController.setShareMedia(sinaContent);
    }


    /**
     * 注销本次登录</br>
     */
    private void logout(final SHARE_MEDIA platform) {
        mController.deleteOauth(activity, platform, new SocializeListeners.SocializeClientListener() {

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int status, SocializeEntity entity) {
                String showText = "解除" + platform.toString() + "平台授权成功";
                if (status != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "解除" + platform.toString() + "平台授权失败[" + status + "]";
                }
                Toast.makeText(activity.getApplicationContext(), showText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 授权。如果授权成功，则获取用户信息</br>
     */
    public void login(final SHARE_MEDIA platform, final ProgressDialog pd) {
        mController.doOauthVerify(activity, platform, new SocializeListeners.UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {
                Log.i(TAG, "start");
                if (pd != null)
                    pd.show();
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
                Log.i(TAG, "platform = " + platform + ",error = " + e);
                if (pd != null)
                    pd.dismiss();
                Toast.makeText(activity.getApplicationContext(), "授权失败...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                if (pd != null)
                    pd.dismiss();
                String uid = value.getString("uid");

                Log.i(TAG, "Bundle = " + value.toString());
                if (!TextUtils.isEmpty(uid)) {
                    getUserInfo(platform, value, pd);
                } else {
                    Toast.makeText(activity.getApplicationContext(), "授权失败...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Log.i(TAG, "platform = " + platform + " cancel auth ");
                if (pd != null)
                    pd.dismiss();
            }
        });
    }

    /**
     * 获取授权平台的用户信息</br>
     * weixin: info = {sex=1, nickname=欧阳, unionid=oFE89t49oQEPTfFaj1beOWgx9tzI, province=北京, openid=o-I5sszDD50HExQxBYuqvlg3wIkU, language=zh_CN, headimgurl=http://wx.qlogo.cn/mmopen/ibLButGMnqJN6P5r9VIPMmPdicxtLN83nVkFWWUlJj1BQBCUbs30o6pVvZ0DZXdBciadaq3BHZZfcTWpIuIeydicKGD9QyAoVbjK/0, country=中国, city=海淀}
     */
    private void getUserInfo(final SHARE_MEDIA platform, final Bundle value, final ProgressDialog pd) {
        if (activity.isFinishing()) {
            return;
        }
        if (pd != null)
            pd.show();
        mController.getPlatformInfo(activity, platform, new SocializeListeners.UMDataListener() {

            @Override
            public void onStart() {
                Log.i(TAG, "start get user info = ");
            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
                if (pd != null)
                    pd.dismiss();
                if (info == null) {
                    Toast.makeText(activity.getApplicationContext(), "获取用户信息失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "status = " + status + ", info = " + info.toString());

                String openid = null;
                String username = null;
                String avatar = null;
                if (platform == SHARE_MEDIA.QQ || platform == SHARE_MEDIA.QZONE) {
                    // String showText = "";
                    username = info.get("screen_name") != null ? info.get("screen_name").toString() : "";
                    avatar = info.get("profile_image_url") != null ? info.get("profile_image_url").toString() : "";
//                    if (status == StatusCode.ST_CODE_SUCCESSED) {
//                        showText = "授权成功，用户名：" + username;
//                        Log.d("#########", "##########" + info.toString());
//                    } else {
//                        showText = "获取用户信息失败";
//                    }
                    openid = value.getString("openid");
                } else if (platform == SHARE_MEDIA.WEIXIN) {
                    username = info.get("nickname") != null ? info.get("nickname").toString() : "";
                    avatar = info.get("headimgurl") != null ? info.get("headimgurl").toString() : "";
                    openid = value.getString("openid");
                } else if (platform == SHARE_MEDIA.SINA) {
                    username = info.get("screen_name") != null ? info.get("screen_name").toString() : "";
                    avatar = info.get("profile_image_url") != null ? info.get("profile_image_url").toString() : "";
                    openid = value.getString("openid");
                    if (TextUtils.isEmpty(openid)) {
                        openid = value.getString("access_token");
                    }
                }
                if (TextUtils.isEmpty(openid) || TextUtils.isEmpty(username)) {
                    Toast.makeText(activity.getApplicationContext(), "获取用户信息失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                String md5 = MD5Tools.toMD5(platform.toString() + openid);
                String sign = md5.substring(md5.length() - 16).toLowerCase();
                MyLog.i("md5=" + md5 + ",sign=" + sign + ",platform=" + platform + ",name =" + username + ",avatar= " + avatar);
                if (callback != null) {
                    callback.onLoginSuccessful(sign, username, platform.toString(), openid, avatar);
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据requestCode获取对应的SsoHandler
        Log.i(TAG, "result code = " + resultCode + ", intent data = " + data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
