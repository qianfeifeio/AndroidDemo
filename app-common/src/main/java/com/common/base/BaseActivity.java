package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.common.base.action.ActivityAction;
import com.common.base.action.HandlerAction;

public  abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity implements ActivityAction, HandlerAction {
    protected VB viewBinding ;

    /**
     * 跳转到指定类
     * {@link com.common.base.action.ActivityAction}
     * 跳转到指定类的指定方法，下面4种都可以
     * {@link com.common.base.action.ActivityAction#getContext} 无参数
     *
     * @see com.common.base.action.ActivityAction#getContext()} 无参数
     */

    @Override
    public Context getContext() {
        return null;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = initViewBinding();
        setContentView(viewBinding.getRoot());
        init();
    }

    protected abstract VB initViewBinding();

    public abstract void init();


    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    /**
     * 防止页面view控件短时间重复点击
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (this.isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 600) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * 字符串非空判断（空值返回“”，有值返回原来的数据）
     *
     * @param checkInfo
     * @return
     */
    protected synchronized String checkEmpty(String checkInfo) {
        //字符串为null或“”的场合
        if (checkInfo == null || checkInfo.length() == 0) {
            return "";
        }
        //空格字符串的场合
        if (checkInfo.trim().length() == 0) {
            return "";
        }
        //去除两头的空格后返回
        return checkInfo.trim();
    }


    public  String cleanEmpty(String inputValue) {
        if (inputValue == null || inputValue.length() == 0 || inputValue.equalsIgnoreCase("null")) {
            return "";
        } else {
            return inputValue;
        }
    }

    public  String cleanEmpty(String inputValue, String defValue) {
        if (inputValue == null || inputValue.length() == 0 || inputValue.equalsIgnoreCase("null")) {
            return defValue;
        } else {
            return inputValue;
        }
    }

}
