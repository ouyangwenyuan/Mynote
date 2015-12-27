package com.funny.note.mynote.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funny.note.mynote.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/12/27.
 */
public class MainFragment extends BaseFragment {


    @ViewInject(R.id.tv_title_left_text)
    protected TextView leftTitleView;
    @ViewInject(R.id.tv_title_right_text)
    protected TextView rightTitleView;
    private View view;
    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fragment_main, null);
        ViewUtils.inject(this, view);
        initViewpager();
        initSwitchBtn();
        return view;
    }


    private void initViewpager() {
        //viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment leftFragment = new AllNoteFragment();
        BaseFragment rightFragment = new GroupFragment();
        fragments.add(leftFragment);
        fragments.add(rightFragment);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initSwitchBtn() {

//        leftTitleView = (TextView) view.findViewById(R.id.tv_title_left_text);
//        leftTitleView.setSelected(true);
//
//        rightTitleView = (TextView) view.findViewById(R.id.tv_title_right_text);
        //rightTitleView.setTextColor(getResources().getColor(R.color.circle_progress_color));
        switchBtn(true);
        leftTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) return;
                switchBtn(true);
                viewPager.setCurrentItem(0);
                index = 0;

            }
        });
        rightTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (index > 0) return;
                switchBtn(false);
                viewPager.setCurrentItem(1);
                index = 1;
//                if(!MeiyunApplication.getSharedPreferencesUtil().getValue("isFirstKnowledgeFragmentIn", false)){
//                    MeiyunApplication.getSharedPreferencesUtil().setValue("isFirstKnowledgeFragmentIn", true);
//                    jumpGuideCover(4);
//                }
            }
        });
    }

    private void switchBtn(boolean selectLeft) {
        leftTitleView.setSelected(selectLeft);
        rightTitleView.setSelected(!selectLeft);
        if (selectLeft) {
            leftTitleView.setBackgroundResource(R.mipmap.title_switch_left_btn_select);
            rightTitleView.setBackgroundResource(R.mipmap.title_switch_right_btn_unselect);
        } else {
            rightTitleView.setBackgroundResource(R.mipmap.title_switch_right_btn_select);
            leftTitleView.setBackgroundResource(R.mipmap.title_switch_left_btn_unselect);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

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

        public MyFragmentPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
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

    }
}
