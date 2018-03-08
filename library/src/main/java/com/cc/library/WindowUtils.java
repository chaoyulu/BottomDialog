package com.cc.library;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by CC on 2018/3/7.
 * 获取屏幕相关参数
 */

public class WindowUtils {
    public static int getWindowWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getWindowHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
