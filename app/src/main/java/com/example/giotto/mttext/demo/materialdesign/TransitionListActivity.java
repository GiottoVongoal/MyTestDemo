package com.example.giotto.mttext.demo.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-06-13 16:39
 * @description
 */
public class TransitionListActivity extends Activity {
    private RecyclerView transition_list_rv;
    private TransitionListAdapter adapter;
    private List<TestBean> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.transition_list_layout);

        transition_list_rv = (RecyclerView) findViewById(R.id.transition_list_rv);
        transition_list_rv.setLayoutManager(new LinearLayoutManager(this));
        initData();
        if (mList != null || mList.size() > 0) {
            adapter = new TransitionListAdapter(mList, this);
            transition_list_rv.setAdapter(adapter);
        }
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestBean bean = new TestBean("结果" + i, "https://b-ssl.duitang.com/uploads/item/201311/19/20131119223735_AaZZC.thumb.700_0.jpeg");
            mList.add(bean);
        }
    }
}
