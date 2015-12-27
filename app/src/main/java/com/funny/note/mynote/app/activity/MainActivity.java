package com.funny.note.mynote.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.funny.note.mynote.R;
import com.funny.note.mynote.app.fragment.BaseFragment;
import com.funny.note.mynote.app.fragment.MainFragment;
import com.funny.note.mynote.app.fragment.MineFragment;
import com.funny.note.mynote.app.manager.GlobalManager;
import com.funny.note.mynote.app.utils.SharedPreferencesUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/12/26.
 */
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.ll_tab1)
    private View mainTab;
    @ViewInject(R.id.ll_tab2)
    private View myTab;
    @ViewInject(R.id.iv_add)
    private View addView;
    @ViewInject(R.id.tv_tab1)
    protected TextView leftTitleView;
    @ViewInject(R.id.tv_tab2)
    protected TextView rightTitleView;
    @ViewInject(R.id.iv_tab1)
    private View leftindicator;
    @ViewInject(R.id.iv_tab2)
    private View rightindicator;

    private ViewPager viewPager;
    private int index = 0;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        initSwitchBtn();
        initViewpager();

        SharedPreferencesUtil sp = GlobalManager.getManager().getSharePreference();
        String user = sp.getValue("username", "");
        String pwd = sp.getValue("password", "");
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
            }
        });
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
        TextView rightBtn = (TextView) findViewById(R.id.tv_right_btn);
    }

    private void initViewpager() {
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment leftFragment = new MainFragment();
        BaseFragment rightFragment = new MineFragment();
        fragments.add(leftFragment);
        fragments.add(rightFragment);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initSwitchBtn() {

        switchBtn(true);
        mainTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) return;
                switchBtn(true);
                viewPager.setCurrentItem(0);
                index = 0;
            }
        });
        myTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (index > 0) return;
                switchBtn(false);
                viewPager.setCurrentItem(1);
                index = 1;
            }
        });
    }

    private void switchBtn(boolean selectLeft) {
        if (selectLeft) {
            rightTitleView.setTextColor(Color.BLACK);
            leftTitleView.setTextColor(getResources().getColor(R.color.login_btn_bg));
            leftindicator.setBackgroundResource(R.mipmap.tab_2_s);
            rightindicator.setBackgroundResource(R.mipmap.tab_4);
        } else {
            leftTitleView.setTextColor(Color.BLACK);
            rightTitleView.setTextColor(getResources().getColor(R.color.login_btn_bg));
            leftindicator.setBackgroundResource(R.mipmap.tab_2);
            rightindicator.setBackgroundResource(R.mipmap.tab_4_s);
        }
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageSelected(int arg0) {
            index = arg0;
            switchBtn(index == 0 ? true : false);
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<BaseFragment> list;

        public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, List<BaseFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public BaseFragment getItem(int arg0) {
            return list.get(arg0);
        }


        String titles[] = {getString(R.string.tab_text_1), getString(R.string.tab_text_2)};

        public String getPageTitle(int position) {
            return titles[position];
        }
    }
}