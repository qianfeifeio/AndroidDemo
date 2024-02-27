package com.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.common.base.action.ActivityAction;
import com.common.base.action.HandlerAction;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity implements ActivityAction, HandlerAction {
    protected T viewBinding;

    /**
     * 跳转到指定类
     * {@link ActivityAction}
     * 跳转到指定类的指定方法，下面4种都可以
     * {@link ActivityAction#getContext} 无参数
     *
     * @see ActivityAction#getContext()} 无参数
     */

    @Override
    public Context getContext() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //返回当前类的父类的Type，也就是BaseActivity
        Type type = this.getClass().getGenericSuperclass();//getGenericSuperclass() 返回的是 Type 对象，它可以包含泛型信息
        //ParameterizedType 对象 :它代表一个具有实际类型参数的泛型类型
        if (type instanceof ParameterizedType) {
            try {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                //获得泛型中的实际类型，可能会存在多个泛型，[0]也就是获得T的type
                Class<T> clazz = (Class<T>) typeArguments[0];
                //从 clazz 所代表的类中查找一个名为"inflate" 的公共方法，该方法接受一个 LayoutInflater 类型的参数，
                // 并返回一个 Method 对象，该对象代表了找到的这个方法。
                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                //方法调用，获得viewBinding实例
                viewBinding = (T) method.invoke(null, getLayoutInflater());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setContentView(viewBinding.getRoot());
        init();
    }


        public abstract void init ();

        /**
         * 防止页面view控件短时间重复点击
         *
         * @param ev
         * @return
         */
        @Override
        public boolean dispatchTouchEvent (MotionEvent ev){
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                if (this.isFastDoubleClick()) {
                    return true;
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        public static long lastClickTime = 0;

        public static boolean isFastDoubleClick () {
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
        protected synchronized String checkEmpty (String checkInfo){
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


        public String cleanEmpty (String inputValue){
            if (inputValue == null || inputValue.length() == 0 || inputValue.equalsIgnoreCase("null")) {
                return "";
            } else {
                return inputValue;
            }
        }

        public String cleanEmpty (String inputValue, String defValue){
            if (inputValue == null || inputValue.length() == 0 || inputValue.equalsIgnoreCase("null")) {
                return defValue;
            } else {
                return inputValue;
            }
        }

    }
