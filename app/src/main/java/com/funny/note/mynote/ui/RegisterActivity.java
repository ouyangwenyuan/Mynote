package com.funny.note.mynote.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.funny.note.mynote.R;
import com.funny.note.mynote.ui.utils.StringUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by admin on 15/12/26.
 */
public class RegisterActivity extends BaseActivity {

    @ViewInject(R.id.et_email_input)
    private EditText etEmail;
    @ViewInject(R.id.et_password_input)
    private EditText etPwd;
    @ViewInject(R.id.et_pwd_again)
    private EditText etPwdAgain;
    @ViewInject(R.id.et_nicename)
    private EditText etNickName;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        setTitleData();
        findViewById(R.id.tv_register_start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    private void doRegister() {
        String email = etEmail.getText().toString();
        String pwd = etPwd.getText().toString();
        String pwdAgain = etPwdAgain.getText().toString();
        String nickName = etNickName.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(getApplicationContext(), "邮箱密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.equalsIgnoreCase(pwdAgain)) {
            Toast.makeText(getApplicationContext(), "两次输入的密码不同", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!StringUtil.isEmail(email)) {
            Toast.makeText(getApplicationContext(), "邮箱格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "注册成功,正在跳转", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));

    }

    private void setTitleData() {
        TextView leftBtn = (TextView) findViewById(R.id.tv_left_btn);
        leftBtn.setBackgroundResource(R.mipmap.meiyun_mouth_back);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView titleView = (TextView) findViewById(R.id.tv_public_title_text);
        titleView.setText(R.string.register_text);
        findViewById(R.id.tv_right_btn).setVisibility(View.GONE);
    }
}
