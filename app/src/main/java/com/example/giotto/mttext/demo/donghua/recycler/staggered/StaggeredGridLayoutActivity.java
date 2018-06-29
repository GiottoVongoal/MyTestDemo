package com.example.giotto.mttext.demo.donghua.recycler.staggered;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;


import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecyler);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.myrecycle);
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(this, mDatas);
        // 瀑布流布局
        // StaggeredGridLayoutManager构造的第二个参数传一个orientation
        // 如果传入的是StaggeredGridLayoutManager.VERTICAL代表有多少列
        // 传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        mDatas.get(position) + "被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        mDatas.get(position) + "被删除了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
