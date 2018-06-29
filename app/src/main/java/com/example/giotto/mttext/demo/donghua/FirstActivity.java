package com.example.giotto.mttext.demo.donghua;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.donghua.objectanimator.OldOMActivity;
import com.example.giotto.mttext.demo.donghua.recycler.MyRecylerActivity;
import com.example.giotto.mttext.demo.donghua.recycler.addscrollview.MyRecycleScrollActivity;
import com.example.giotto.mttext.demo.donghua.recycler.headerbottom.HeaderBottomActivity;
import com.example.giotto.mttext.demo.donghua.recycler.pull.PullRecyclerViewActivity;
import com.example.giotto.mttext.demo.donghua.recycler.staggered.StaggeredGridLayoutActivity;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @version $version$
 * @Title $title$
 * @Description $desc$
 * @auther $user$
 * @time $date$ $time$
 */
public class FirstActivity extends Activity {
    private ViewStub myViewStub;
    private TextView donghua_tv, recyclerview_tv, recyclerview_add_scrollview_tv,
            headerbottom_recyclerview_tv, staggeredgrid_recyclerview_tv, recyclerview_pull_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mergelayout);
        setContentView(R.layout.first);
        myViewStub = (ViewStub) findViewById(R.id.viewstub);
        if (myViewStub != null) {
            myViewStub.inflate();
            TextView textview = (TextView) findViewById(R.id.textview);
            textview.setText("我是viewstub布局,显示吧!!!");
        }
        //动画
        donghua_tv = (TextView) findViewById(R.id.donghua_tv);
        donghua_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, OldOMActivity.class);
            }
        });
        //RecyclerView布局
        recyclerview_tv = (TextView) findViewById(R.id.show_recyclerview_tv);
        recyclerview_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, MyRecylerActivity.class);
            }
        });
        //RecyclerView与ScrollView滑动冲出
        recyclerview_add_scrollview_tv = (TextView) findViewById(R.id.recyclerview_add_scrollview_tv);
        recyclerview_add_scrollview_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, MyRecycleScrollActivity.class);
            }
        });
        //RecyclerView增加Header和Bottom
        headerbottom_recyclerview_tv = (TextView) findViewById(R.id.headerbottom_recyclerview_tv);
        headerbottom_recyclerview_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, HeaderBottomActivity.class);
            }
        });
        //瀑布流
        staggeredgrid_recyclerview_tv = (TextView) findViewById(R.id.staggeredgrid_recyclerview_tv);
        staggeredgrid_recyclerview_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, StaggeredGridLayoutActivity.class);
            }
        });
        //RecyclerView下拉刷新上拉加载
        recyclerview_pull_tv = (TextView) findViewById(R.id.recyclerview_pull_tv);
        recyclerview_pull_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.intentStartActivity(FirstActivity.this, PullRecyclerViewActivity.class);
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                //进行资源释放操作
                break;
        }
    }
}
