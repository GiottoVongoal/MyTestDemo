package com.example.giotto.mttext.demo.springview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hao on 2016/12/20.
 */

public class ObservableScrollView extends ScrollView {

    private OnScrollChangeListener scrollViewListener = null;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(OnScrollChangeListener onScrollChangeListener) {
        this.scrollViewListener = onScrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface OnScrollChangeListener {

        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }
}