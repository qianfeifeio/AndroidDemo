package com.common.base.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:常用的一些控件属性文字格式限制等
 * @author:Heshuanghua create at 2018-11-15
 */
public class MyCommonUtil {
    /**
     * 计算时间差
     *
     * @param starTime
     * @param endTime
     * @return 返回时间差 毫秒值
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timediffer = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);
            long diff = parse1.getTime() - parse.getTime();
            timediffer = diff + "";

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timediffer;

    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        } else {
            sb.append("0分");
        }
//        if (second > 0) {
//            sb.append(second + "秒");
//        }
//        if (milliSecond > 0) {
//            sb.append(milliSecond + "毫秒");
//        }
        return sb.toString();
    }


    /**
     * EditText：限制输入金额为2位小数
     * 输入小数超过2位就禁止输入
     *
     * @param editText
     */
    public void pickTwoDecimal(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

                //如果.在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                //如果起始位置为0并且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    //判断字符串是不是以数字开头
    public static boolean isStartWithNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str.charAt(0) + "");
            // Matcher isNum = pattern.matcher(str);
            if (!isNum.matches()) {
                return false;
            }else{
                return true;
            }
        }
    }


    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (" ".equals(source) || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * EditText动态限制字数
     */
    public static void setEditTextLengthLimit(EditText editText, int length) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    /**
     * 保留两位小数
     *
     * @param number
     * @return
     */
    public static String getDoubleDecimal(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        String strValue = df.format(number);
        return strValue;
    }

    /**
     * 为TextView 添加删除线
     *
     * @param textView 传入的控件
     * @param flag     标记位：true添加删除线，false去除删除线
     * @return
     */
    public static void setPaintFlags(TextView textView, boolean flag) {
        if (flag == true) {
            //设置删除线代码：
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            //去除删除线代码：
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }


    /**
     * 限制EditText输入长度
     *
     * @param mEditText
     * @param length    限制输入文字的长度
     */
    public static void setEditTextLength(EditText mEditText, int length) {
        if (length > 0) {
            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
        }
    }

    public static void hideAllInputMethod(Activity act) {
        // 从系统服务中获取输入法管理器
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) { // 软键盘如果已经打开则关闭之
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideOneInputMethod(Activity act, View v) {
        // 从系统服务中获取输入法管理器
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 关闭屏幕上的输入法软键盘
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
