package com.coopbuy.mall.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.coopbuy.mall.ui.module.center.activity.OrderActivity;

/**
 * 页面跳转
 * @author ymb
 * Create at 2017/7/18 17:25
 */
public class IntentUtils {

    public static final String PARAM1 = "param1";
    public static final String PARAM2 = "param2";
    public static String TAB_INDEX = "index";
    public static String DATA = "data";
    public static String ORDER_TYPE = "order_type";
    public static String EVENT = "event";
    public static String INFO = "info";
    public static String BOO = "boo";
    public static final int REQUEST_SEARCH = 1;
    public static String TYPE = "type";//区别进入某个界面的类型
    /**
     * 无参无需返回结果
     * @param context
     * @param activity
     */
    public static void gotoActivity(Context context, Class<?> activity) {
        context.startActivity((new Intent(context, activity)));
    }

    /**
     * 带一个参数无返回结果
     * @param context
     * @param activity
     * @param param1
     */
    public static void gotoActivity(Context context, Class<?> activity, String param1) {
        Intent intent = new Intent(context, activity);
        intent.putExtra(PARAM1, param1);
        context.startActivity(intent);
    }
    /**
     * 我的订单
     *
     * @param context
     * @param type
     */
    public static void gotoMeOrderActivity(Context context, int type) {
       /* if (type == Constants.ORDER_TYPE_AFTERSALES)
            context.startActivity((new Intent(context, ApplyForCustomerServiceListActivity.class)));
        else*/
            context.startActivity((new Intent(context, OrderActivity.class).putExtra(ORDER_TYPE, type)));
    }
}
