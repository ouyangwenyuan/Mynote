package com.funny.note.mynote.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.funny.note.mynote.R;
import com.funny.note.mynote.app.utils.StringUtil;
import com.funny.note.mynote.app.utils.ThirdPartyHandler;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ThirdPartyHandler.OnThirdPartyLoginFinish {

    @ViewInject(R.id.et_user_name)
    private EditText etEmail;
    @ViewInject(R.id.et_password)
    private EditText etPwd;
    @ViewInject(R.id.iv_qq_login_btn)
    private ImageView qqLogin;

    @ViewInject(R.id.iv_wechat_login_btn)
    private ImageView weixinLogin;

    @ViewInject(R.id.iv_weibo_login_btn)
    private ImageView weiboLogin;

    @ViewInject(R.id.tv_login_start_btn)
    private View loginBtn;
    @ViewInject(R.id.tv_forgot_pwd)
    private View forgotPwdBtn;

    @ViewInject(R.id.tv_public_title_text)
    TextView tvTitle;
    @ViewInject(R.id.tv_right_btn)
    TextView rightView;
    @ViewInject(R.id.tv_left_btn)
    TextView leftView;
    View titleView;

    private ProgressDialog pd;
    private ThirdPartyHandler thirdPartyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        setTitleData();
        loginBtn.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weiboLogin.setOnClickListener(this);
        weixinLogin.setOnClickListener(this);
        forgotPwdBtn.setOnClickListener(this);
        thirdPartyHandler = new ThirdPartyHandler(this);
        thirdPartyHandler.setCallback(this);
        pd = new ProgressDialog(this);
        pd.setMessage("logining...");
    }


    private void setTitleData() {
        rightView.setText(R.string.register_text);
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        tvTitle.setText(R.string.login_text);
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_start_btn:
                doLogin(0);
                break;
            case R.id.iv_qq_login_btn:
                doLogin(1);
                break;
            case R.id.iv_wechat_login_btn:
                doLogin(2);
                break;
            case R.id.iv_weibo_login_btn:
                doLogin(3);
                break;
            case R.id.tv_forgot_pwd:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    private void doLogin(int type) {

        if (type == 1) {
            thirdPartyHandler.login(SHARE_MEDIA.QQ);
        } else if (type == 2) {
            thirdPartyHandler.login(SHARE_MEDIA.WEIXIN);
        } else if (type == 3) {
            thirdPartyHandler.login(SHARE_MEDIA.SINA);
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        String email = etEmail.getText().toString();
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(getApplicationContext(), "邮箱密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!StringUtil.isEmail(email)) {
            Toast.makeText(getApplicationContext(), "邮箱格式不对", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.equals("ouyang@163.com") && pwd.equals("123456")) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }

//        pd.show();
//        //TODO  login server
//        if (pd != null) {
//            pd.dismiss();
//        }
    }

    @Override
    public void onLoginSuccessful(String sign, String userName, String platform, String openid, String avatarUrl) {
        //第三方登录成功,拿第三方登录信息去开户.
        if (pd != null) {
            pd.dismiss();
        }
    }

    @Override
    public void onLoginFail(String error) {
        //第三方登录失败
        if (pd != null) {
            pd.dismiss();
        }
    }
}
