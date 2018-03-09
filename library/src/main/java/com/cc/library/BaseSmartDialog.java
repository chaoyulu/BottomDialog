package com.cc.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CC on 2018/3/7.
 */

public abstract class BaseSmartDialog<T> extends DialogFragment implements AnimationCallback {
    protected Context mContext;
    protected boolean isOutside = false; // 点击的区域是否在mView区域以外
    protected boolean isCancelableOutside = true; // 是否支持点击外部消失
    protected int layoutRes;

    protected int[] padding = {30, 30, 30, 30}; // Dialog的内边距

    protected String title = "Smart标题";
    protected RecyclerView.Adapter adapter;
    public static final int ORIENTATION_VERTICAL = 0x100;
    public static final int ORIENTATION_GRID = 0x101;
    protected int orientation = ORIENTATION_VERTICAL; // 默认方向垂直
    protected int spanCount = 3; // RecyclerView每一行3列
    protected List<String> list = new ArrayList<>();


    public View mView; // 指对话框显示View的区域
    protected View decorView; // 对话框所有区域，包括灰色阴影

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除Dialog的标题栏
        mView = inflater.inflate(getLayoutRes(), container, false);
        AnimManager.getInstance().showAnimation(mView);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mContext == null) throw new NullPointerException("must invoke init() method first");
        Window window = getDialog().getWindow();
        if (window == null) return;
        decorView = window.getDecorView();
        decorView.setPadding(padding[0], padding[1], padding[2], padding[3]);
        decorView.setBackground(new ColorDrawable(Color.TRANSPARENT)); // 设置Dialog背景透明
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowUtils.getWindowWidth(mContext);
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);

        getDialog().setCanceledOnTouchOutside(false); // 屏蔽系统带的点击外部区域隐藏对话框，自己定义

        decorView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // 计算点击的区域是否在Dialog外部
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    float touchX = motionEvent.getRawX(); // 手指点击的地方（相对于整个屏幕）
                    float touchY = motionEvent.getRawY();
                    // 计算可视Dialog区域的边界
                    int[] positions = new int[2];
                    mView.getLocationOnScreen(positions);
                    // Dialog区域的View相对于整个屏幕的位置
                    int left = positions[0];
                    int top = positions[1];
                    int right = left + mView.getWidth();
                    int bottom = top + mView.getHeight();
                    isOutside = touchX < left || touchX > right || touchY < top || touchY > bottom;
                    Log.i("onTouch", (getLayoutRes() == R.layout.smart_default_dialog_layout) + "/");
                    if (isOutside && isCancelableOutside)
                        AnimManager.getInstance().dismissAnimation(mView, BaseSmartDialog.this);
                }
                return false;
            }
        });

        // 默认提供的布局
        if (getLayoutRes() == R.layout.smart_default_dialog_layout) {
            TextView tvTitle = (TextView) mView.findViewById(R.id.tv_title);
            tvTitle.setText(title);
            TextView tvCancel = (TextView) mView.findViewById(R.id.tv_cancel);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AnimManager.getInstance().dismissAnimation(mView, BaseSmartDialog.this);
                }
            });
            RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
            LinearLayoutManager manager = null;
            if (orientation == ORIENTATION_VERTICAL) {
                manager = new LinearLayoutManager(mContext);
            } else if (orientation == ORIENTATION_GRID) {
                manager = new GridLayoutManager(mContext, spanCount);
            }
            recyclerView.setLayoutManager(manager);
            if (adapter == null) {
                adapter = new SmartAdapter(mContext, list);
            }
            recyclerView.setAdapter(adapter);
        }
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public View getDecorView() {
        return decorView;
    }

    @Override
    public void onAnimationEnd() {
        dismiss();
    }
}
