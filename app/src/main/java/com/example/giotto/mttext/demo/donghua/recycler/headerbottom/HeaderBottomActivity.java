package com.example.giotto.mttext.demo.donghua.recycler.headerbottom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giotto.mttext.demo.R;


public class HeaderBottomActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HeaderBottomAdapter adapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecyler);
        mRecyclerView = (RecyclerView) findViewById(R.id.myrecycle);
        //List布局
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter = new HeaderBottomAdapter(this));

        //Grid布局
        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);//这里用线性宫格显示 类似于grid view
        mRecyclerView.setAdapter(adapter = new HeaderBottomAdapter(this));
        // 如果你的RecyclerView使用Grid类型列表在设置Adapter后需要调用这个方法，
        // 根据当前Item类型来判断占据的横向格数，这也是Adapter里面实现isHeaderView和isBottomView的缘故
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (adapter.isHeaderView(position) || adapter.isBottomView(position)) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }
}