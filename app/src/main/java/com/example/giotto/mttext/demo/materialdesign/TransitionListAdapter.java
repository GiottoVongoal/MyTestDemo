package com.example.giotto.mttext.demo.materialdesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.bean.TestBean;
import com.example.giotto.mttext.demo.glidetest.GlideImgManager;
import com.example.giotto.mttext.demo.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-06-13 16:45
 * @description
 */
public class TransitionListAdapter extends RecyclerView.Adapter<TransitionListAdapter.TransitionListViewHolder> {
    private List<TestBean> mList = new ArrayList<>();
    private Context context;

    public TransitionListAdapter(List<TestBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public TransitionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transition_list_item_layout, parent, false);
        return new TransitionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransitionListViewHolder holder, final int position) {
        final TestBean bean = mList.get(position);
        GlideImgManager.loadCircleImage(context, bean.getCheckItem(), holder.transition_item_iv);
        holder.transition_item_tv.setText(bean.getCheckResult());
        holder.transition_item_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.showToast(context, "position = " + position);
                Intent intent = new Intent(context, TransitionTwo_Activity.class);
                // Pass data object in the bundle and populate details activity.
                intent.putExtra("path", bean.getCheckItem());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, (View) (holder.transition_item_iv), "transition_list");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class TransitionListViewHolder extends RecyclerView.ViewHolder {
        ImageView transition_item_iv;
        TextView transition_item_tv;

        public TransitionListViewHolder(View itemView) {
            super(itemView);
            transition_item_iv = (ImageView) itemView.findViewById(R.id.transition_item_iv);
            transition_item_tv = (TextView) itemView.findViewById(R.id.transition_item_tv);
        }
    }
}
