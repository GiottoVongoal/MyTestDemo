package com.example.giotto.mttext.demo.activitys;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import java.util.List;

/**
 * @Description: 从底部弹出的ListView
 * @author:
 */
public class CarOrderTrackingPopupWindow extends PopupWindow {

    private TextView dialog_title_tv;
    private RecyclerView dialog_lv;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private RelativeLayout dialog_rl;//只有一个item时的显示
    private TextView dialog_tips_tv;//tips
    private TextView dialog_finish_tv;
    private View mMenuView;
    private Animation animation;

    public CarOrderTrackingPopupWindow(Context context, String title, List<String> list, int place, String tips,
                                       MyAdapter.OnItemClickListener onItemClickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.car_order_center_settlement_dialog, null);
        dialog_title_tv = (TextView) mMenuView.findViewById(R.id.car_order_center_settlement_dialog_title_tv);
        dialog_lv = (RecyclerView) mMenuView.findViewById(R.id.car_order_center_settlement_dialog_lv);
        dialog_rl = (RelativeLayout) mMenuView.findViewById(R.id.car_order_center_settlement_dialog_rl);
        dialog_tips_tv = (TextView) mMenuView.findViewById(R.id.car_order_center_settlement_dialog_tips_tv);
        dialog_finish_tv = (TextView) mMenuView.findViewById(R.id.car_order_center_settlement_dialog_finish_tv);
        dialog_title_tv.setText(title);
        if (list != null) {
            dialog_rl.setVisibility(View.GONE);
            if (list.size() == 1) {
                dialog_rl.setVisibility(View.VISIBLE);
                dialog_tips_tv.setText(tips);
            }
            //创建线性布局,垂直方向
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            //给RecyclerView设置布局管理器
            dialog_lv.setLayoutManager(layoutManager);
            adapter = new MyAdapter(context, place, list);
            dialog_lv.setAdapter(adapter);
            adapter.setOnItemClickListener(onItemClickListener);

        }
        dialog_finish_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom);
        mMenuView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.car_order_center_settlement_dialog_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        setContentView(mMenuView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.popup_main_background)));
        setFocusable(true);
    }


}
