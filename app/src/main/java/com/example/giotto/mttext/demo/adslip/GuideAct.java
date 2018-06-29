package com.example.giotto.mttext.demo.adslip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.giotto.mttext.demo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangweiyi on 16/6/12.
 */
public class GuideAct extends AppCompatActivity {
    Spinner spinner;
    String[] mItems = {"Accordion", "Tablet", "CubeIn", "CubeOut", "FlipVertical", "FlipHorizontal"
            , "Stack", "ZoomIn", "ZoomOut", "RotateUp", "RotateDown", "Accordion",};
    //ZoomIn
    JazzyViewPager viewPager;
    private List<View> viewPagerList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_guide);
        EventBus.getDefault().register(this);//订阅
        spinner = (Spinner) findViewById(R.id.spinner);
        viewPager = (JazzyViewPager) findViewById(R.id.jvp_guide);
        spinner.setPrompt("请选择");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        viewPagerList = new ArrayList<>();
        viewPagerList.add(new View1(this));
        viewPagerList.add(new View2(this));
        viewPagerList.add(new View3(this));
//        for (int i = 0; i < 4; i++) {
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            GlideImgManager.loadImage(this, imageArr[i], imageView);
//            viewPagerList.add(imageView);
//        }

        viewPager.setTransitionEffect(JazzyViewPager.TransitionEffect.ZoomIn);
        viewPager.setFadeEnabled(true);
        viewPager.setPageMargin(0);

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View view, Object object) {
                if (view instanceof OutlineContainer) {
                    return ((OutlineContainer) view).getChildAt(0) == object;
                } else {
                    return view == object;
                }
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewPager.findViewFromObject(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewPagerList.get(position));
                viewPager.setObjectForPosition(viewPagerList.get(position),
                        position);
                return viewPagerList.get(position);
            }

            @Override
            public int getCount() {
                return viewPagerList.size();
            }
        });
    }

    @Subscribe
    public void onEvent(MessageEvent event) {
    /* Do something */
        viewPager.setCurrentItem(event.getCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
