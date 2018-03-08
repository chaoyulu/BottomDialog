package com.cc.library;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by CC on 2018/3/7.
 */

public class AnimManager {

    private static volatile AnimManager instance;
    private long duration = 500;

    public static AnimManager getInstance() {
        if (instance == null) {
            synchronized (AnimManager.class) {
                if (instance == null) {
                    instance = new AnimManager();
                }
            }
        }
        return instance;
    }

    public AnimManager showAnimation(View view) {
        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0F,
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF, 1F,
                Animation.RELATIVE_TO_SELF, 0F);
        animation.setDuration(duration);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        Log.i("showAnimation", view + "/");
        if (view != null) view.startAnimation(animation);
        return this;
    }

    public AnimManager duration(long duration) {
        this.duration = duration;
        return this;
    }

    public AnimManager dismissAnimation(View view) {
        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0F,
                Animation.RELATIVE_TO_SELF, 0F, Animation.RELATIVE_TO_SELF, 0F,
                Animation.RELATIVE_TO_SELF, 1F);
        animation.setDuration(duration);
        animation.setDuration(duration);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        Log.i("dismissAnimation", view + "/");
        view.startAnimation(animation);
        return this;
    }
}
