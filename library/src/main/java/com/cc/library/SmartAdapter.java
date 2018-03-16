package com.cc.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CC on 2018/3/8.
 */

public class SmartAdapter extends RecyclerView.Adapter<SmartAdapter.SmartViewHolder> {

    private List<Item> mList;
    private LayoutInflater mInflater;
    private int orientation = LinearLayout.VERTICAL;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public SmartAdapter(Context context, List<Item> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmartViewHolder(mInflater.inflate(R.layout.item_default, parent, false));
    }

    @Override
    public void onBindViewHolder(SmartViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Item item = mList.get(position);
        holder.tvName.setText(item.getName());
        holder.container.setOrientation(orientation);

        // 无图模式
        if (item.getIcon() == 0) {
            holder.ivIcon.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.tvName.getLayoutParams();
            params.height = WindowUtils.dp2px(mContext, 50);
            params.setMargins(60, 0, 0, 0);
            holder.tvName.setTextSize(20);
            holder.tvName.setLayoutParams(params);
        } else {
            holder.ivIcon.setVisibility(View.VISIBLE);
            holder.ivIcon.setImageResource(item.getIcon());
            // 有图模式
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.ivIcon.getLayoutParams();
            if (orientation == LinearLayout.VERTICAL) {
                holder.tvName.setGravity(Gravity.CENTER);
                params.gravity = Gravity.CENTER;
                holder.tvName.setTextSize(14);
            } else if (orientation == LinearLayout.HORIZONTAL) {
                holder.tvName.setTextSize(16);
            }
            params.setMargins(20, 0, 20, 20);
            holder.ivIcon.setLayoutParams(params);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(position, item);
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null)
                    mOnItemLongClickListener.onItemLongClick(position, item);
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
        LinearLayout container;
        ImageView ivIcon;

        SmartViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
