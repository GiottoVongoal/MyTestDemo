package com.example.giotto.mttext.demo.huadongdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.giotto.mttext.demo.R;


/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.huadongdemo
 * @date 2016-12-12 14:37
 * @description
 */
public class FragmentTwo extends Fragment {
    private RelativeLayout pay_bottom_layout;
    private FrameLayout flayout;
    private ScrollView show_scrollview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scroll_layout, container, false);
        pay_bottom_layout = (RelativeLayout) view.findViewById(R.id.pay_bottom_layout);
        flayout = (FrameLayout) view.findViewById(R.id.flayout);
        pay_bottom_layout.setVisibility(View.GONE);
        flayout.setVisibility(View.GONE);
        //
        show_scrollview = (ScrollView) view.findViewById(R.id.show_scrollview);
        show_scrollview.setOnTouchListener(new View.OnTouchListener() {
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
                        int scrollViewMeasuredHeight = show_scrollview.getChildAt(0).getMeasuredHeight();
                        if (scrollY == 0) {
                            Log.i("scroll", "滑动到了顶端 getScrollY()=" + scrollY);
                            ((HuadongActicity) getActivity()).rl_tv1_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv2_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv3_bottom.setVisibility(View.VISIBLE);
                            ((HuadongActicity) getActivity()).rl_tv4_bottom.setVisibility(View.VISIBLE);
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
