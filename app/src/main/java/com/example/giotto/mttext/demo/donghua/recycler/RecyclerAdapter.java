package com.example.giotto.mttext.demo.donghua.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.giotto.mttext.demo.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> mData;
    Context context;

    //创建点击事件
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);

        void onItemLongClickListener(View view, int position);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecyclerAdapter(Context context, List<String> Data) {
        mData = Data;
        this.context = context;
    }

    // item显示类型
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mytext, parent, false);
        return new ViewHolder(view);
    }

    // 数据的绑定显示
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //建立起VIewHolder中试视图与数据的关联
        holder.textView.setText(mData.get(position));
        holder.button.setText("点我增加一个item");
        //增加点击事件
        if (null != itemClickListener) {
            // 为item增加点击事件
            holder.ll_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(v, position);
                }
            });
            holder.ll_view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClickListener.onItemLongClickListener(v, position);
                    return true;
                }
            });
            // 为单个控件增加点击事件
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addData(position);
                    // 从被增加的Item后面开始刷新，这样就保持了原有的添加动画
                    notifyItemRangeChanged(position, getItemCount());
                    Toast.makeText(context, mData.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addData(int position) {
        mData.add(position, "Insert One,我是增加的");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button button;
        public LinearLayout ll_view;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
            button = (Button) itemView.findViewById(R.id.button);
            ll_view = (LinearLayout) itemView.findViewById(R.id.ll_view);
        }
    }
}
