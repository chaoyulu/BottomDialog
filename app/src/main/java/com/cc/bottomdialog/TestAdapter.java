package com.cc.bottomdialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CC on 2018/3/8.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        public TestViewHolder(View itemView) {
            super(itemView);
        }
    }
}
