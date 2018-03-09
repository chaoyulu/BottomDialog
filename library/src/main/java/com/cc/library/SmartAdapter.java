package com.cc.library;

import android.annotation.SuppressLint;
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

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public SmartAdapter(Context context, List<String> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmartViewHolder(mInflater.inflate(R.layout.item_default, parent, false));
    }

    @Override
    public void onBindViewHolder(SmartViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvName.setText(mList.get(position));

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(position);
            }
        });

        holder.tvName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null)
                    mOnItemLongClickListener.onItemLongClick(position);
                return true;
            }
        });
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
