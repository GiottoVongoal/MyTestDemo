package com.example.giotto.mttext.demo.picturefullscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * @Author yly
 * @Data 2018/5/8 11:20
 * @PackageName com.example.giotto.mttext.demo.picturefullscreen
 * @Description  
 */
public class MyListView extends ListView {
    private int mMaxOverDistance = 100;
    private Context mContext;

    public MyListView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }


    private void init() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float dendity = metrics.density;
        mMaxOverDistance = (int) (dendity * mMaxOverDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);

    }
}
