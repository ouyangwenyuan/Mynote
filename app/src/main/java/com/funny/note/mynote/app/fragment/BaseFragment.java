package com.funny.note.mynote.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

public abstract class BaseFragment extends Fragment {

    // protected TextView titleView;
    //
    // @Override
    // public View onCreateView(LayoutInflater inflater, ViewGroup container,
    // Bundle savedInstanceState) {
    // View view = inflater.inflate(R.layout.fragment_record, null);
    // titleView = (TextView) view.findViewById(R.id.tv_title);
    // titleView.setText("base");
    // return view;
    // }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen"); //统计页面
    }

}
