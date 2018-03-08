package com.cc.library;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by CC on 2018/3/7.
 */

public class SmartDialog extends BaseSmartDialog {

    public SmartDialog create(Context context) {
        mContext = context;
        return this;
    }

    @Override
    public int getLayoutRes() {
        Log.i("LayoutRes", "++++++++++++++++++++");
        return layoutRes;
    }

    public SmartDialog setLayoutRes(@LayoutRes int layoutRes) {
        Log.i("LayoutRes", "====================");
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
}
