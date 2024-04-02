package com.andorid.demo;

import com.android.demo.databinding.ActivityMainBinding;
import com.common.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public void init() {
            viewBinding.text.setText("666");
    }


}