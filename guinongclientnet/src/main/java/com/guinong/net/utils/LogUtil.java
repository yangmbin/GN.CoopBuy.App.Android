package com.guinong.net.utils;

import android.util.Log;


/**
 * 创建者:wangyu
 * 创建时间:2016/3/1 14:05
 * 功能说明:打印log工具类
 */
public class LogUtil {
    public static boolean IS_DEBUG = true;

    public static void info(String tag, String logStr) {
        if (IS_DEBUG && logStr != null) {
            Log.i(tag, logStr);
        }

    }

    public static void error(String tag, String logStr) {
        if (IS_DEBUG && logStr != null) {
            Log.e(tag, logStr);
        }
    }


}