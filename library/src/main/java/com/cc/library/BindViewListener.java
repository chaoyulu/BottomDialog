package com.cc.library;

import android.view.View;

/**
 * Created by CC on 2018/3/15.
 * 回调View，对view进行事件监听
 */

public interface BindViewListener {
    void bind(View dialogView, BaseSmartDialog dialog);
}
