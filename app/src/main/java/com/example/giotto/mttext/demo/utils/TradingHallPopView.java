package com.example.giotto.mttext.demo.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;


/**
 * @version $version$
 * @Title $title$
 * @Description $desc$
 * @auther $user$
 * @time $date$ $time$
 */
public class TradingHallPopView {
    private PopupWindow mPopupWindow;
    private AnimationDrawable animation;
    private Context context;

    public TradingHallPopView(Context context) {
        this.context = context;
    }

    public void showServicePop(String tvString, View myView) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_tradinghall_coloregg_gif,
                null);
        ImageView pop_tradinghall_egg_iv = (ImageView) view.findViewById(R.id.pop_tradinghall_egg_iv);
        TextView tv = (TextView) view.findViewById(R.id.pop_tradinghall_egg_tv);
        TextView pop_tradinghall_egg_close = (TextView) view.findViewById(R.id.pop_tradinghall_egg_close);
        tv.setText(tvString);
        pop_tradinghall_egg_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animation != null) {
                    animation.stop();//停止
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
//			mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            mPopupWindow.setFocusable(false);
            mPopupWindow.setOutsideTouchable(true);
        }
        mPopupWindow.showAtLocation(myView, Gravity.CENTER, 0, 0);
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            pop_tradinghall_egg_iv.setBackgroundResource(R.drawable.bg_round_white);
            animation = (AnimationDrawable) pop_tradinghall_egg_iv.getBackground();
            animation.start();//启动
        }
    }
}
