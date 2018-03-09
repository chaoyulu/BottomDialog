package com.cc.library;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by CC on 2018/3/7.
 */

public class SmartDialog extends BaseSmartDialog {
    public SmartDialog init(Context context) {
        mContext = context;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return layoutRes;
    }

    public SmartDialog layoutRes(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }

    public SmartDialog display() {
        if (mContext == null) throw new NullPointerException("must invoke create() method first");
        if (!isAdded()) {
            if (mContext instanceof FragmentActivity)
                show(((FragmentActivity) mContext).getSupportFragmentManager(), "");
        }
        return this;
    }

    public SmartDialog cancelableOutside(boolean isCancelableOutside) {
        this.isCancelableOutside = isCancelableOutside;
        return this;
    }

    public SmartDialog padding(int left, int top, int right, int bottom) {
        padding[0] = left;
        padding[1] = top;
        padding[2] = right;
        padding[3] = bottom;
        return this;
    }

    // 设置RecyclerView展示方向
    public SmartDialog recyclerViewOrientation(int orientation) {
        if (orientation != SmartDialog.ORIENTATION_VERTICAL && orientation != SmartDialog.ORIENTATION_GRID)
            throw new IllegalArgumentException(
                    "orientation must is one of in （BaseSmartDialog.ORIENTATION_VERTICAL , " +
                            "BaseSmartDialog.ORIENTATION_GRID）");
        this.orientation = orientation;
        return this;
    }

    // 设置RecyclerView为Grid时的列数
    public SmartDialog spanCount(int spanCount) {
        if (spanCount < 1) throw new IllegalArgumentException("spanCount need >= 1");
        this.spanCount = spanCount;
        return this;
    }

    // 设置自定义适配器
    public SmartDialog adapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public SmartDialog items(List<String> items) {
        list = items;
        return this;
    }

    public SmartDialog title(String title) {
        this.title = title;
        return this;
    }

    public View getCustomView() {
        return mView;
    }
}
