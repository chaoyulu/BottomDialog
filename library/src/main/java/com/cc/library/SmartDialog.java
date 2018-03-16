package com.cc.library;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by CC on 2018/3/7.
 */

public class SmartDialog extends BaseSmartDialog {
    private BindViewListener mBindViewListener;

    public SmartDialog init(Context context) {
        mContext = context;
        return this;
    }

    @Override
    public int getLayoutRes() {
        return layoutRes;
    }

    @Override
    protected void bindView(View dialogView) {
        if (mBindViewListener != null) mBindViewListener.bind(dialogView, this);
    }

    public SmartDialog bindViewListener(BindViewListener bindViewListener) {
        mBindViewListener = bindViewListener;
        return this;
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

    public SmartDialog dialogHeight(@IntRange(from = 0) int dialogHeight) {
        this.dialogHeight = dialogHeight;
        return this;
    }

    public SmartDialog dialogWidth(@IntRange(from = 0) int dialogWidth) {
        this.dialogWidth = dialogWidth;
        return this;
    }

    // 只对默认布局有效
    public SmartDialog backgroundRes(@DrawableRes int backgroundRes) {
        this.backgroundRes = backgroundRes;
        return this;
    }

    // 是否可设置背景资源
    public SmartDialog backgroundResEnable(boolean enable) {
        backgroundResEnable = enable;
        return this;
    }

    public SmartDialog padding(int left, int top, int right, int bottom) {
        padding[0] = left;
        padding[1] = top;
        padding[2] = right;
        padding[3] = bottom;
        return this;
    }

    public SmartDialog padding(int padding) {
        this.padding[0] = padding;
        this.padding[1] = padding;
        this.padding[2] = padding;
        this.padding[3] = padding;
        return this;
    }

    public SmartDialog onItemClick(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        return this;
    }

    public SmartDialog onItemLongClick(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
        return this;
    }

    public SmartDialog onOutsideClick(OutsideClickListener outsideClickListener) {
        mOutsideClickListener = outsideClickListener;
        return this;
    }

    public SmartDialog titleVisible(boolean titleVisible) {
        this.titleVisible = titleVisible;
        return this;
    }

    public SmartDialog cancelVisible(boolean cancelVisible) {
        this.cancelVisible = cancelVisible;
        return this;
    }

    public SmartDialog animEnable(boolean animEnable) {
        this.animEnable = animEnable;
        return this;
    }

    public SmartDialog gravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public SmartDialog titleGravity(int titleGravity) {
        this.titleGravity = titleGravity;
        return this;
    }

    public SmartDialog titleColor(@ColorRes int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public SmartDialog titleSize(@IntRange(from = 1) int titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    public SmartDialog itemOrientation(int itemOrientation) {
        if (itemOrientation != LinearLayout.HORIZONTAL && itemOrientation != LinearLayout.VERTICAL)
            throw new IllegalArgumentException("itemOrientation must is one of in （LinearLayout.HORIZONTAL , " +
                    "LinearLayout.VERTICAL）");
        this.itemOrientation = itemOrientation;
        return this;
    }

    // 设置RecyclerView展示方向
    public SmartDialog recyclerViewOrientation(int orientation) {
        if (orientation != BaseSmartDialog.ORIENTATION_VERTICAL && orientation != BaseSmartDialog.ORIENTATION_GRID)
            throw new IllegalArgumentException("orientation must is one of in （BaseSmartDialog.ORIENTATION_VERTICAL , " +
                    "BaseSmartDialog.ORIENTATION_GRID）");
        this.orientation = orientation;
        return this;
    }

    // 设置RecyclerView为Grid时的列数
    public SmartDialog spanCount(@IntRange(from = 1) int spanCount) {
        this.spanCount = spanCount;
        return this;
    }

    public SmartDialog animDuration(@IntRange(from = 0) long duration) {
        this.duration = duration;
        return this;
    }

    // 背景灰暗度
    public SmartDialog dimAmount(@FloatRange(from = 0.5, to = 1) float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    // Dialog透明度
    public SmartDialog alpha(@FloatRange(from = 0.1, to = 1) float alpha) {
        this.alpha = alpha;
        return this;
    }

    // 设置自定义适配器
    public SmartDialog adapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public SmartDialog items(List<Item> items) {
        list = items;
        return this;
    }

    public SmartDialog title(String title) {
        this.title = title;
        return this;
    }
}
