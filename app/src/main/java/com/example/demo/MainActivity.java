package com.example.demo;

import android.view.LayoutInflater;

import com.common.base.BaseActivity;
import com.example.demo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public ActivityMainBinding  initViewBinding() {
        return ActivityMainBinding.inflate(LayoutInflater.from(this));
    }

    @Override
    public void init() {
            viewBinding.text.setText("666");
    }


}