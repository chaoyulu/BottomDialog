package com.cc.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CC on 2018/3/8.
 */

public class SmartAdapter extends RecyclerView.Adapter<SmartAdapter.SmartViewHolder> {

    private List<String> mList;
    private LayoutInflater mInflater;

    public SmartAdapter(Context context, List<String> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmartViewHolder(mInflater.inflate(R.layout.item_default, parent, false));
    }

    @Override
    public void onBindViewHolder(SmartViewHolder holder, int position) {
        holder.tvName.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class SmartViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        SmartViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
