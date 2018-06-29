package com.example.giotto.mttext.demo.huadongdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.giotto.mttext.demo.R;


/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.huadongdemo
 * @date 2016-12-12 14:37
 * @description
 */
public class FragmentOne extends Fragment {
    private ScrollView caidan_scrollview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_tradinghall_coloregg_gif, container, false);
        caidan_scrollview = (ScrollView) view.findViewById(R.id.caidan_scrollview);
        caidan_scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 手指压下屏幕
                        break;
                    case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动
                        break;
                    case MotionEvent.ACTION_UP: // 手指离开屏幕
                        int scrollY = view.getScrollY();
                        int height = view.getHeight();
                        int scrollViewMeasuredHeight = caidan_scrollview.getChildAt(0).getMeasuredHeight();
                        if (scrollY == 0) {
                            Log.i("scroll", "滑动到了顶端 getScrollY()=" + scrollY);
                            ((HuadongActicity) getActivity()).rl_tv1_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv2_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv3_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv4_bottom.setVisibility(View.VISIBLE);
//                            int strokeWidth = 1;  // 1dp 边框宽度
//                            int roundRadius = 5;  // 5dp 圆角半径
//                            int strokeColor = Color.parseColor("#FFFF0000");//边框颜色
//                            int fillColor = Color.parseColor("#FF00FF00"); //内部填充颜色
//                            GradientDrawable gd = new GradientDrawable();//创建drawable
//                            gd.setColor(fillColor);
//                            gd.setCornerRadius(roundRadius);
//                            gd.setStroke(strokeWidth, strokeColor);
//                            ((HuadongActicity) getActivity()).rl1.setBackgroundDrawable(gd);// 创建渐变的shape drawable
//                            int colors[] = {0xff255779, 0xff3e7492, 0xffa6c0cd};//分别为开始颜色，中间夜色，结束颜色
//                            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
//                            ((HuadongActicity) getActivity()).rl1.setBackgroundDrawable(gradientDrawable);
                        } else {
                            ((HuadongActicity) getActivity()).rl_tv1_bottom.setVisibility(View.GONE);
                            ((HuadongActicity) getActivity()).rl_tv2_bottom.setVisibility(View.GONE);
                            ((HuadongActicity) getActivity()).rl_tv3_bottom.setVisibility(View.GONE);
                            ((HuadongActicity) getActivity()).rl_tv4_bottom.setVisibility(View.GONE);
                        }
                        if ((scrollY + height) == scrollViewMeasuredHeight) {
                            Log.i("scroll", "滑动到了底部 scrollY=" + scrollY);
                            Log.i("scroll", "滑动到了底部 height=" + height);
                            Log.i("scroll", "滑动到了底部 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        return view;
    }
}
