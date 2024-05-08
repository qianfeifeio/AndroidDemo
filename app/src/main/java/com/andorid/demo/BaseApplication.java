package com.andorid.demo;

import android.app.Application;

import com.air4.chinesetts.tts.TtsManager;

/**
 * Description:
 *
 * @Author qianfei
 * @Create 2024/5/8
 * @Version 1.0
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //TensorFlowTTS文字转中文语音
        TtsManager.getInstance().init(this);
    }
}
