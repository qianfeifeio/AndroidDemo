package com.common.base.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by Administrator
 * on 2017/7/31.
 * Android Studio 中会自动生成BuildConfig.java文件
 * 可以通过此文件获得Apk的VersionCode、 VersionName、isDebug
 */
@SuppressWarnings("unused")
public class VersionUtil {
    /**
     * 获取版本名
     * 使用 BuildConfig.VERSION_NAME 替换
     * @param context 上下文
     * @return 版本号
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }

    /**
     * 获取版本code
     * 使用 BuildConfig.VERSION_CODE 替换
     * @param context 上下文
     * @return 版本code
     */
    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String getDeviceIP() {  
      try {  
          for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
              NetworkInterface intf = en.nextElement();  
              for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
                  InetAddress inetAddress = enumIpAddr.nextElement();  
                  if (!inetAddress.isLoopbackAddress()) {  
                      return inetAddress.getHostAddress().toString();  
                  }  
              }  
          }  
      } catch (Exception ex) {  
        ex.printStackTrace();  
      }  
      return null;  
  }

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前Android系统的版本（9、10、。。。14）
     */
    public static String getAndroidVersion(Context context) {
        //获取操作系统版本
        String osVersion = android.os.Build.VERSION.RELEASE;
        return osVersion;


    }

}
