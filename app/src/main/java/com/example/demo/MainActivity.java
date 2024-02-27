package com.example.demo;

import com.common.base.BaseActivity;
import com.example.demo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public void init() {
            viewBinding.text.setText("666");
    }


}