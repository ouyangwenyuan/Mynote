package com.funny.note.mynote.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.funny.note.mynote.R;
import com.funny.note.mynote.ui.utils.StringUtil;
import com.funny.note.mynote.ui.utils.ThirdPartyHandler;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LoginActivity extends BaseActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        setTitleData();
        etEmail = (EditText) findViewById(R.id.et_user_name);
        etPwd = (EditText) findViewById(R.id.et_password);
        findViewById(R.id.tv_login_start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO login
                doLogin();
            }
        });
        ThirdPartyHandler thirdPartyHandler = new ThirdPartyHandler(this);
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("logining...");
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
    }

    private void setTitleData() {
        findViewById(R.id.tv_right_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        findViewById(R.id.tv_left_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
