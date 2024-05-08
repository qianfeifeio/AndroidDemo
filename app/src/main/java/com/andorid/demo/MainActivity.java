package com.andorid.demo;

import com.air4.chinesetts.tts.TtsManager;
import com.android.demo.databinding.ActivityMainBinding;
import com.common.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public void init() {
            viewBinding.text.setText("666");

        //语音提示
        TtsManager.getInstance().speak("记得双击，666", 0.9f, true);
    }


}