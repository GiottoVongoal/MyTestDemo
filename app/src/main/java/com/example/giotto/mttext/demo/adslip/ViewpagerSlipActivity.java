package com.example.giotto.mttext.demo.adslip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.adslip
 * @date 2017-01-23 17:19
 * @description 平滑过渡的ViewPager广告条(修改了3个页面出错的问题)
 */
public class ViewpagerSlipActivity extends AppCompatActivity {
    private PageChangeIndicatorView pciIndicator;
    private Handler handler;
    private boolean isSwitchPager = false;//默认不切换
    private String[] imgDescArr = new String[]{"标题1", "标题2", "标题3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_slip_layout);

        final ViewPager vpBitmap = (ViewPager) findViewById(R.id.vp_Bitmap);
        pciIndicator = (PageChangeIndicatorView) findViewById(R.id.pci_indicator);

        handler = new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                // super.handleMessage(msg);
                //更新当前的ViewPager 要显示的当前条目
                vpBitmap.setCurrentItem(vpBitmap.getCurrentItem() + 1, true);
            }
        };

        vpBitmap.setAdapter(new SerialPagerAdapter(this, getBitmapList(), imgDescArr));

        pciIndicator.setPageCount(getBitmapList().size());  //设置下标点的个数

//        //设置自动切换
//        Observable.interval(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        vpBitmap.setCurrentItem(vpBitmap.getCurrentItem() + 1, true);
//                    }
//                });

        //实现自动切换功能
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!isSwitchPager) {
                    SystemClock.sleep(2000);
                    //拿着我们创建的handler发消息
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();

        //通过监听页面切换切换下标
        vpBitmap.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pciIndicator.onPageSelectedUpdate(position % getBitmapList().size());    //切换下标
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 创建一个bitmap列表模拟数据
     *
     * @return bitmap列表
     */
    public List<Bitmap> getBitmapList() {
        Bitmap bitmap01 = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        Bitmap bitmap02 = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
        Bitmap bitmap03 = BitmapFactory.decodeResource(getResources(), R.mipmap.c);
        List<Bitmap> bitmapList = new ArrayList<>();
        bitmapList.add(bitmap01);
        bitmapList.add(bitmap02);
        bitmapList.add(bitmap03);
        return bitmapList;
    }

    @Override
    protected void onDestroy() {
        //当activity销毁时,把是否切换的标记设置为true
        isSwitchPager = true;
        super.onDestroy();
    }

}
