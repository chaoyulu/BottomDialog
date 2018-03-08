package com.cc.library;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by CC on 2018/3/7.
 */

public abstract class BaseSmartDialog extends DialogFragment {
    protected Context mContext;
    public View mView;
    protected View dialogView;
    protected int layoutRes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除Dialog的标题栏
        mView = inflater.inflate(getLayoutRes(), container, false);
        AnimManager.getInstance().showAnimation(mView);
//        dialogView = getDialog().getWindow().getDecorView();
        return mView;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public void onStart() {
        super.onStart();
        if (mContext == null) throw new NullPointerException("must invoke create() method first");
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowUtils.getWindowWidth(mContext);
        attributes.gravity = Gravity.BOTTOM;

        window.setAttributes(attributes);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.i("dismiss", mView + "/");
        if (mView != null)
            AnimManager.getInstance().dismissAnimation(mView);
    }
}
