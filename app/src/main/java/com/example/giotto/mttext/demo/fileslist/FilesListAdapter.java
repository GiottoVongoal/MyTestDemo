package com.example.giotto.mttext.demo.fileslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.activitys
 * @date 2016-11-07 15:05
 * @description
 */
public class FilesListAdapter extends RecyclerView.Adapter<FilesListAdapter.ViewHolder> {
    private List<FilesBean> list;
    private final Context context;

    //创建点击事件
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FilesListAdapter(Context context, List<FilesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.file_list_item_tv.setText(list.get(position).getName());
        //增加点击事件
        if (null != itemClickListener) {
            // 为item增加点击事件
            holder.dialog_item_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(v, position);
                }
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView file_list_item_tv;
        public LinearLayout dialog_item_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            file_list_item_tv = (TextView) itemView.
                    findViewById(R.id.file_list_item_tv);
            dialog_item_ll = (LinearLayout) itemView.
                    findViewById(R.id.car_order_center_settlement_dialog_item_ll);
        }
    }
}