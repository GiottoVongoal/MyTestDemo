package com.example.giotto.mttext.demo.donghua.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.InterruptedIOException;

/**
 * 为ListView添加分割线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    //默认分割条Drawable资源的Id
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    //分割条的Drawable对象
    private Drawable mDivider;
    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        //获取分割条的drawable对象
        mDivider = typedArray.getDrawable(0);
        //回收ta所占用的空间
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            try {
                throw new InterruptedIOException("invalid orientation");
            } catch (InterruptedIOException e) {
                e.printStackTrace();
            }
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //获取列表项距离上边源的距离
        int top = parent.getPaddingTop();
        //获取列表项距离下边源的距离
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        //获取列表的总数
        final int childCount = parent.getChildCount();
        //开始绘制这些列表项之间的分割线
        for (int i = 0; i < childCount; i++) {
            //获取当前的列表
            final View child = parent.getChildAt(i);
            //获取当前列表项的布局参数信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //计算分割条左上角的的纵坐标
            final int left = child.getRight() + params.rightMargin;
            //计算分割条右下角的纵坐标
            final int right = left + mDivider.getIntrinsicHeight();
            //设置分割条绘制的位置
            mDivider.setBounds(left, top, right, bottom);
            //开始绘制当前列表项下方的分割条
            mDivider.draw(c);

        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //获取列表项距离左边源的距离
        int left = parent.getPaddingLeft();
        //获取列表项距离右边源的距离
        int right = parent.getWidth() - parent.getPaddingRight();
        //获取列表的总数
        int childCount = parent.getChildCount();
        //开始绘制这些列表项之间的分割线
        for (int i = 0; i < childCount; i++) {
            //获取当前的列表
            View child = parent.getChildAt(i);
            //获取当前列表项的布局参数信息
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //计算分割条左上角的的纵坐标
            int top = child.getBottom() + params.bottomMargin;
            //计算分割条右下角的纵坐标
            int bottom = top + mDivider.getIntrinsicHeight();
            //设置分割条绘制的位置
            mDivider.setBounds(left, top, right, bottom);
            //开始绘制当前列表项下方的分割条
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}

