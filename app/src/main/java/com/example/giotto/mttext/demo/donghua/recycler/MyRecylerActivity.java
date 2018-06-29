package com.example.giotto.mttext.demo.donghua.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class MyRecylerActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    // GridView布局
//    private GridLayoutManager layoutManager;
    private List<String> list = new ArrayList<>();

    private FloatingActionButton recycler_fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecyler);
        for (int i = 0; i < 30; i++) {
            list.add("我是RecyclerView新布局" + i);
        }
        recyclerView = (RecyclerView) findViewById(R.id.myrecycle);
        recycler_fab = (FloatingActionButton) findViewById(R.id.recycler_fab);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局,垂直方向
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        // GridView布局
//        layoutManager = new GridLayoutManager(this, 4);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器,并且设置
        adapter = new RecyclerAdapter(this, list);
        //保证一定在setLayoutManager方法之后调用该方法！！尤其是你使用RecyclerView显示Header视图的时候。
        recyclerView.setAdapter(adapter);
        //增加分割线
        //ListView分割线
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //GridView分割线
//        recyclerView.addItemDecoration(
//                new DividerGridItemDecoration(MyRecylerActivity.this));
        // 设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //增加点击事件
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                MyUtils.showToast(MyRecylerActivity.this, "点击了" + position);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                MyUtils.showToast(MyRecylerActivity.this, "删除了" + position);
                adapter.removeData(position);
                // 从被删除的Item后面开始刷新，这样就保持了原有的删除动画
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        });

        //增加滚动显示一键置顶按钮监听
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollYDistance = MyUtils.getScollYDistance(layoutManager);
                Log.i("scrollYDistance->", scrollYDistance + "");
                if (scrollYDistance > 200) {
                    moveOaUp(recycler_fab);
                } else {
                    moveOaDown(recycler_fab);
                }
            }
        });
        recycler_fab.setOnClickListener(BackTopListener);
    }

    /**
     * 一键返回顶部监听
     */
    private final View.OnClickListener BackTopListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            recyclerView.smoothScrollToPosition(0);
        }
    };

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    //FloatingActionButton向上滑入屏幕
    private void moveOaUp(View view) {
        recycler_fab.setVisibility(View.VISIBLE);
        ViewCompat.animate(view).translationY(-200)
                .setInterpolator(INTERPOLATOR).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
    }

    //FloatingActionButton向下滑出屏幕
    private void moveOaDown(View view) {
        ViewCompat.animate(view).translationY(200)
                .setInterpolator(INTERPOLATOR).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
//                        recycler_fab.setvisibility(view.gone);

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
    }
}
