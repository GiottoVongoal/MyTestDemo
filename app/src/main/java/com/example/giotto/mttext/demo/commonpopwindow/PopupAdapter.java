package com.example.giotto.mttext.demo.commonpopwindow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import java.util.List;

/**
 * @Author yly
 * @Data 2018/5/10 11:27
 * @PackageName com.example.giotto.mttext.demo.commonpopwindow
 * @Description  
 */
public class PopupAdapter  extends RecyclerView.Adapter<PopupAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> list;
    private MyOnclickListener myItemClickListener;

    public PopupAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnItemClickListener(MyOnclickListener listener) {
        this.myItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.choice_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {
                    myItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView choice_text;

        public MyViewHolder(final View itemView) {
            super(itemView);
            choice_text = (TextView) itemView.findViewById(R.id.choice_text);
        }
    }
}
