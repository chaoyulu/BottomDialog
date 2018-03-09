package com.cc.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public abstract class BaseSmartDialog extends DialogFragment implements AnimationCallback {
    protected Context mContext;
    protected boolean isOutside = false; // 点击的区域是否在mView区域以外
    protected boolean isCancelableOutside = true; // 是否支持点击外部消失

    @LayoutRes
    protected int layoutRes = R.layout.smart_default_dialog_layout;

    protected int[] padding = {30, 30, 30, 30}; // Dialog的内边距

    protected String title = "Smart标题";
    protected boolean titleVisible = true; // 是否显示标题
    protected boolean cancelVisible = true; // 是否显示取消按钮
    protected RecyclerView.Adapter adapter;
    public static final int ORIENTATION_VERTICAL = 0x100;
    public static final int ORIENTATION_GRID = 0x101;
    protected int orientation = ORIENTATION_VERTICAL; // 默认方向垂直
    protected int spanCount = 3; // RecyclerView每一行3列
    protected List<String> list = new ArrayList<>();
    protected int dialogHeight;
    protected long duration = 500; // 进出动画时长

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;
    protected RecyclerView.ItemDecoration mItemDecoration;

    @DrawableRes
    protected int backgroundRes = R.drawable.shape_round_corner; // 设置dialog的背景布局文件
    protected boolean backgroundResEnable = true; // 是否可设置背景资源

    public View mView; // 指对话框显示View的区域
    protected View decorView; // 对话框所有区域，包括灰色阴影

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除Dialog的标题栏
        mView = inflater.inflate(getLayoutRes(), container, false);
        AnimManager.getInstance().duration(duration).showAnimation(mView);
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
        // 当设置了dialogHeight，则根据设置的高度显示，否则自适应高度
        if (dialogHeight > 0) attributes.height = dialogHeight;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);

        calculateOutside();
        initDefaultView();
    }

    /**
     * 计算Dialog以外区域的点击事件，点击外部触发Dialog消失动画
     */
    private void calculateOutside() {
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
                    if (isOutside && isCancelableOutside)
                        AnimManager.getInstance().duration(duration).dismissAnimation(mView, BaseSmartDialog.this);
                }
                return false;
            }
        });
    }

    private void initDefaultView() {
        // 默认提供的布局
        if (getLayoutRes() == R.layout.smart_default_dialog_layout) {
            TextView tvTitle = (TextView) mView.findViewById(R.id.tv_title);
            if (titleVisible) {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(title);
            } else {
                tvTitle.setVisibility(View.GONE);
            }

            TextView tvCancel = (TextView) mView.findViewById(R.id.tv_cancel);
            if (cancelVisible) {
                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AnimManager.getInstance().dismissAnimation(mView, BaseSmartDialog.this);
                    }
                });
            } else {
                tvCancel.setVisibility(View.GONE);
            }
            RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
            LinearLayoutManager manager = null;
            if (orientation == ORIENTATION_VERTICAL) manager = new LinearLayoutManager(mContext);
            else if (orientation == ORIENTATION_GRID)
                manager = new GridLayoutManager(mContext, spanCount);
            recyclerView.setLayoutManager(manager);
            if (adapter == null) adapter = new SmartAdapter(mContext, list);
            recyclerView.setAdapter(adapter);

            if (mItemDecoration != null) recyclerView.addItemDecoration(mItemDecoration);

            if (backgroundResEnable) {
                mView.findViewById(R.id.container).setBackgroundResource(backgroundRes);
                tvCancel.setBackgroundResource(backgroundRes);
            }

            if (mOnItemClickListener != null) {
                ((SmartAdapter) adapter).setOnItemClickListener(mOnItemClickListener);
            }

            if (mOnItemLongClickListener != null) {
                ((SmartAdapter) adapter).setOnItemLongClickListener(mOnItemLongClickListener);
            }
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
