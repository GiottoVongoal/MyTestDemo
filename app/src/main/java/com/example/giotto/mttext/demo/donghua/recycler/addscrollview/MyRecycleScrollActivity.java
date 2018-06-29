package com.example.giotto.mttext.demo.donghua.recycler.addscrollview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.donghua.recycler.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.donghua.recycler.addscrollview
 * @date 2017-03-31 16:00
 * @description ScrollView嵌套RecyclerView 在6.0以及以上版本显示不完全
 * (在RecyclerView 外面套一层RelativeLayout就可以了。)
 * <p>
 * //解决ScrollView与RecyclerView嵌套滑动冲突(RecyclerView外边需再嵌套一层RelativeLayout)
 * mRecyclerBacklog.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
 * @Override public boolean canScrollVertically() {
 * return false;
 * }
 * });
 */
public class MyRecycleScrollActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecyler_add_scrollview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //recyclerView去除焦点(scrollView 嵌套 RecyclerView 显示时会滚动到RecyclerView第一项)
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.requestFocus();
        recyclerView.setNestedScrollingEnabled(false);
        for (int i = 0; i < 10; i++) {
            list.add("我是RecyclerView的Item" + i);
        }
        adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
