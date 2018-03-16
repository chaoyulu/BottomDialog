package com.cc.library;

/**
 * Created by CC on 2018/3/16.
 * 点击的是否是Dialog外部区域
 */

public interface OutsideClickListener {
    void outsideClick(boolean isOutside, BaseSmartDialog dialog);
}
