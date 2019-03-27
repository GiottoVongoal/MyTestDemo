package com.example.giotto.mttext.demo.activitys;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.scrollviewtop.HuaDongActivity;
import com.example.giotto.mttext.demo.adslip.GuideAct;
import com.example.giotto.mttext.demo.adslip.ViewpagerSlipActivity;
import com.example.giotto.mttext.demo.bannar.BannarActivity;
import com.example.giotto.mttext.demo.bean.TestBean;
import com.example.giotto.mttext.demo.collectoractivitytext.OneActivity;
import com.example.giotto.mttext.demo.commonpopwindow.PopupWindowActivity;
import com.example.giotto.mttext.demo.constraintlayout.ConstraintLayoutActivity;
import com.example.giotto.mttext.demo.dateview.MyDateActivity;
import com.example.giotto.mttext.demo.donghua.FirstActivity;
import com.example.giotto.mttext.demo.dropdownmenu.DropDownActivity;
import com.example.giotto.mttext.demo.fileslist.FilesListActivity;
import com.example.giotto.mttext.demo.foodmenu.FoodMenuActivity;
import com.example.giotto.mttext.demo.fragmentdemo.FragmentDemoActivity;
import com.example.giotto.mttext.demo.glidetest.GlideImageViewActivity;
import com.example.giotto.mttext.demo.huadongdemo.HuadongActicity;
import com.example.giotto.mttext.demo.linechart.DrawLineChartActivity;
import com.example.giotto.mttext.demo.lottie.LottieTestActivity;
import com.example.giotto.mttext.demo.materialdesign.BottomNavigationViewActivity;
import com.example.giotto.mttext.demo.materialdesign.DesignTabLayoutActivity;
import com.example.giotto.mttext.demo.materialdesign.MaterialDesignActivity;
import com.example.giotto.mttext.demo.materialdesign.MaterialDesignBasicActivity;
import com.example.giotto.mttext.demo.materialdesign.MaterialDesignBasicTwoActivity;
import com.example.giotto.mttext.demo.materialdesign.MaterialDesignTwoActivity;
import com.example.giotto.mttext.demo.materialdesign.TransitionActivity;
import com.example.giotto.mttext.demo.materialdesign.TransitionListActivity;
import com.example.giotto.mttext.demo.mycustomviewltest.MyDemoSonActivity;
import com.example.giotto.mttext.demo.picturefullscreen.PictureFullScreenActivity;
import com.example.giotto.mttext.demo.scaleimageview.ScaleImageViewActivity;
import com.example.giotto.mttext.demo.search.SearchActivity;
import com.example.giotto.mttext.demo.smarttab.SmartTabTestActivity;
import com.example.giotto.mttext.demo.springview.AcFunHeader;
import com.example.giotto.mttext.demo.springview.SpringView;
import com.example.giotto.mttext.demo.springview.SpringViewActivity;
import com.example.giotto.mttext.demo.timecount.GetVerificationCodeActivity;
import com.example.giotto.mttext.demo.timecount.VerificationCodeActivity;
import com.example.giotto.mttext.demo.utils.MyUtils;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TextShowActivity extends Activity {
    private TextView tv;
    private SpringView spring_view;

    /**************************************/
    private CarOrderTrackingPopupWindow menuWindow;
    int place = 0;

    /**************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textshow_layout);
        MyUtils.verifyStoragePermissions(this);

        spring_view = (SpringView) findViewById(R.id.spring_view);
        spring_view.setGive(SpringView.Give.NONE);
        spring_view.setHeader(new AcFunHeader(this, 0));
        spring_view.setFooter(new AcFunHeader(this, 0));

        getChannelNotification("title", "我是message");

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CustomProgressDialog dialog = new CustomProgressDialog(this, "正在加载中");
//                dialog.show();

//                new TradingHallPopView(this).showServicePop("车易拍补贴您" + 123 + "元", tv);
                MyUtils.intentStartActivity(TextShowActivity.this, OneActivity.class);
            }
        });
        //使用方法设置最后的快递单号为蓝色
        String s1 = "您的物品已交付物品发出。快递单号为";
        String s2 = "109248019284";
        setTextViewColor(this, tv, this.getResources().getColor(R.color.blue3),
                s1 + s2, s1.length(), (s1 + s2).length());
        //使用代码添加TextView
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        TextView tv1 = new TextView(this);
        tv1.setPadding(10, 10, 10, 10);
        LayoutParams lp1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.addView(tv1, lp1);
        //地址变蓝
        String ss1 = "车辆已完成市场外检，您可以到北京市京南服务中心看车了";
        String ss2 = "北京市京南服务中心";
        int i = ss1.indexOf(ss2);
        Log.i("---i--->", i + "");//14
        setTextViewColor(this, tv1, this.getResources().getColor(R.color.blue3),
                ss1, i, i + ss2.length());
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, ScrollActivity.class);
            }
        });

        TextView tv2 = new TextView(this);
        tv2.setPadding(10, 10, 10, 10);
        LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.addView(tv2, lp2);
        //List拼成String
        final List<String> list = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            String string = "北京市京南服务中" + j;
            list.add(string);
        }
        Log.i("---list--->", setTextString(list));
        tv2.setText(setTextString(list));
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, FirstActivity.class);
            }
        });

        TextView tv3 = new TextView(this);
        tv3.setPadding(10, 10, 10, 10);
        LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.addView(tv3, lp3);
        //List模型类拼成String
        List<TestBean> modelList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            TestBean model = new TestBean();
            model.setCheckItem("我是情况" + j);
            model.setCheckResult("属实" + j);
            modelList.add(model);
        }
        Log.i("---modelList--->", setTextModel(modelList));
        tv3.setText(setTextModel(modelList));
        //增强for循环的使用
        List<TestBean> stringList = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            TestBean model = new TestBean("情况" + k, "结果" + k);
            stringList.add(model);
        }
        for (TestBean myList : stringList) {
            Log.v("myList", myList.getCheckItem() + "--" + myList.getCheckResult());
        }
        setDoubleOrInt();
        setDateTime();
        //获取网络时间
        //请求网络资源是耗时操作。放到子线程中进行
        new Thread(new Runnable() {
            @Override
            public void run() {
                setNetTime();
            }
        }).start();
        testLong();
        //Glide加载图片
        TextView tv4 = new TextView(this);
        tv4.setPadding(10, 10, 10, 10);
        LayoutParams lp4 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout.addView(tv4, lp4);
        tv4.setText("点我去GlideActivity页面");
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, GlideImageViewActivity.class);
            }
        });
        TextView tv5 = new TextView(this);
        tv5.setPadding(10, 10, 10, 10);
        LayoutParams lp5 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv5, lp5);
        tv5.setText("点我弹出ListView");
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 实例化SelectPicPopupWindow
                Log.i("place--->外部", place + "");
                menuWindow = new CarOrderTrackingPopupWindow(TextShowActivity.this, "我是标题",
                        list, place, "", new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        MyUtils.showToast(TextShowActivity.this, list.get(position));
                        place = position;
                        Log.i("place--->内部", place + "");
                        menuWindow.dismiss();
                    }
                });
                // 显示窗口
                menuWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        TextView tv6 = new TextView(this);
        tv6.setPadding(10, 10, 10, 10);
        LayoutParams lp6 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv6, lp6);
        tv6.setText("点我跳到Fragment布局Demo页面");
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, FragmentDemoActivity.class);
            }
        });
        TextView tv7 = new TextView(this);
        tv7.setPadding(10, 10, 10, 10);
        LayoutParams lp7 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv7, lp7);
        tv7.setText("点我跳到仿支付宝四个活动,下拉导航变大的动画");
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, HuadongActicity.class);
            }
        });
        TextView tv8 = new TextView(this);
        tv8.setPadding(10, 10, 10, 10);
        LayoutParams lp8 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv8, lp8);
        tv8.setText("点我去平滑过渡的ViewPager广告条页面");
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, ViewpagerSlipActivity.class);
            }
        });
        TextView tv9 = new TextView(this);
        tv9.setPadding(10, 10, 10, 10);
        LayoutParams lp9 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv9, lp9);
        tv9.setText("点我跳到倒计时页面");
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, GetVerificationCodeActivity.class);
            }
        });
        TextView tv10 = new TextView(this);
        tv10.setPadding(10, 10, 10, 10);
        LayoutParams lp10 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv10, lp10);
        tv10.setText("图片点击一下就放大到全屏,再点一下就回到原界面");
        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, PictureFullScreenActivity.class);
            }
        });
        TextView tv11 = new TextView(this);
        tv11.setPadding(10, 10, 10, 10);
        LayoutParams lp11 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv11, lp11);
        tv11.setText("点我去点菜的菜单页面");
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, FoodMenuActivity.class);
            }
        });
        TextView tv12 = new TextView(this);
        tv12.setPadding(10, 10, 10, 10);
        LayoutParams lp12 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv12, lp12);
        tv12.setText("点我去Lottie测试类页面");
        tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, LottieTestActivity.class, "hello-world.json");
            }
        });
        TextView tv13 = new TextView(this);
        tv13.setPadding(10, 10, 10, 10);
        LayoutParams lp13 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv13, lp13);
        tv13.setText("点我去ConstraintLayoutActivity页面");
        tv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, ConstraintLayoutActivity.class);
            }
        });
        TextView tv14 = new TextView(this);
        tv14.setPadding(10, 10, 10, 10);
        LayoutParams lp14 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv14, lp14);
        tv14.setText("点我去MaterialDesign视觉设计语言页面");
        tv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, MaterialDesignActivity.class);
            }
        });
        TextView tv15 = new TextView(this);
        tv15.setPadding(10, 10, 10, 10);
        LayoutParams lp15 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv15, lp15);
        tv15.setText("点我去MaterialDesign视觉设计语言页面--2");
        tv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, MaterialDesignTwoActivity.class);
            }
        });
        TextView tv16 = new TextView(this);
        tv16.setPadding(10, 10, 10, 10);
        LayoutParams lp16 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv16, lp16);
        tv16.setText("点我去MaterialDesign基础--阴影显示");
        tv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, MaterialDesignBasicActivity.class);
            }
        });
        TextView tv17 = new TextView(this);
        tv17.setPadding(10, 10, 10, 10);
        LayoutParams lp17 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv17, lp17);
        tv17.setText("点我去Transition页面");
        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, TransitionActivity.class);
            }
        });
        TextView tv18 = new TextView(this);
        tv18.setPadding(10, 10, 10, 10);
        LayoutParams lp18 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv18, lp18);
        tv18.setText("点我去TransitionList页面");
        tv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, TransitionListActivity.class);
            }
        });
        final TextView tv19 = new TextView(this);
        tv19.setPadding(10, 10, 10, 10);
        LayoutParams lp19 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv19, lp19);
        tv19.setText("点我去MaterialDesign基础第二页面");
        tv19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TextShowActivity.this, MaterialDesignBasicTwoActivity.class);
                String transitionName = "circle_reveal";
                ActivityOptions transitionActivityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(TextShowActivity.this, tv19, transitionName);
                startActivityForResult(i, 1, transitionActivityOptions.toBundle());
//                MyUtils.intentStartActivity(TextShowActivity.this, MaterialDesignBasicTwoActivity.class);
            }
        });
        TextView tv20 = new TextView(this);
        tv20.setPadding(10, 10, 10, 10);
        LayoutParams lp20 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv20, lp20);
        tv20.setText("点我去DesignTabLayout页面");
        tv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, DesignTabLayoutActivity.class);
            }
        });
        TextView tv21 = new TextView(this);
        tv21.setPadding(10, 10, 10, 10);
        LayoutParams lp21 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv21, lp21);
        tv21.setText("点我去BottomNavigationViewActivity页面");
        tv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, BottomNavigationViewActivity.class);
            }
        });
        TextView tv22 = new TextView(this);
        tv22.setPadding(10, 10, 10, 10);
        LayoutParams lp22 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv22, lp22);
        tv22.setText("点我去DropDownActivity页面");
        tv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, DropDownActivity.class);
            }
        });
        TextView tv23 = new TextView(this);
        tv23.setPadding(10, 10, 10, 10);
        LayoutParams lp23 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv23, lp23);
        tv23.setText("点我去展示历史查询页面");
        tv23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, SearchActivity.class);
            }
        });
        TextView tv24 = new TextView(this);
        tv24.setPadding(10, 10, 10, 10);
        LayoutParams lp24 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv24, lp24);
        tv24.setText("点我去日历控件页面");
        tv24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, MyDateActivity.class);
            }
        });
        TextView tv25 = new TextView(this);
        tv25.setPadding(10, 10, 10, 10);
        LayoutParams lp25 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv25, lp25);
        tv25.setText("点我去ViewPager特效滑动页面");
        tv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, GuideAct.class);
            }
        });
        TextView tv26 = new TextView(this);
        tv26.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp26 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv26, lp26);
        tv26.setText("点我去检查控件是否划出屏幕页面");
        tv26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, CheckViewIsShowActivity.class);
            }
        });
        TextView tv27 = new TextView(this);
        tv27.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp27 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv27, lp27);
        tv27.setText("点我去输入6位验证码页面");
        tv27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, VerificationCodeActivity.class);
            }
        });
        TextView tv28 = new TextView(this);
        tv28.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp28 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv28, lp28);
        tv28.setText("点我去折线图");
        tv28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, DrawLineChartActivity.class);
            }
        });
        TextView tv29 = new TextView(this);
        tv29.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp29 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv29, lp29);
        tv29.setText("点我去ScrollView向上滑动标题栏渐变效果页面");
        tv29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, HuaDongActivity.class);
            }
        });
        TextView tv30 = new TextView(this);
        tv30.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp30 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv30, lp30);
        tv30.setText("点我去简单的自定义控件页面");
        tv30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, MyDemoSonActivity.class);
            }
        });
        TextView tv31 = new TextView(this);
        tv31.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp31 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv31, lp31);
        tv31.setText("点我去vivi中带粘性的viewPager滑动效果页面");
        tv31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, SmartTabTestActivity.class);
            }
        });
        TextView tv32 = new TextView(this);
        tv32.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp32 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv32, lp32);
        tv32.setText("点我去封装一个通用的PopupWindow页面");
        tv32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, PopupWindowActivity.class);
            }
        });
        TextView tv33 = new TextView(this);
        tv33.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp33 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv33, lp33);
        tv33.setText("点我去vivi中带粘性的ScrollView,上滑下滑都显示粘性效果");
        tv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, SpringViewActivity.class);
            }
        });
        TextView tv34 = new TextView(this);
        tv34.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp34 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv34, lp34);
        tv34.setText("点我去通过手势随意缩放、移动ImageView图片页面");
        tv34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, ScaleImageViewActivity.class);
            }
        });
        TextView tv35 = new TextView(this);
        tv35.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp35 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv35, lp35);
        tv35.setText("点我去广告轮播图页面");
        tv35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, BannarActivity.class);
            }
        });
        TextView tv36 = new TextView(this);
        tv36.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp36 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(tv36, lp36);
        tv36.setText("点我去扫描文件夹下内容页面");
        tv36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(TextShowActivity.this, FilesListActivity.class);
            }
        });

        String cut = CutString("012{012{012", "{");
        Log.i("cutString", cut);//{012{0
    }

    private String CutString(String str, String startStr) {
        int begin;
        begin = str.indexOf(startStr, 0); //开始位置
        return str.substring(begin, str.length() - 2); //取搜索的条数，用结束的位置-开始的位置,并返回
    }

    private void setTextViewColor(Context context, TextView tv, int color,
                                  String string, int start, int end) {
        SpannableStringBuilder builder = new SpannableStringBuilder(string);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        builder.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }

    private String setTextString(List<String> list) {
        String disputeContent = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                disputeContent += list.get(i) + ";";
            }
        }
        return disputeContent;
    }

    private String setTextModel(List<TestBean> list) {
        String Check = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i).getCheckItem() + list.get(i).getCheckResult() + ";";
                Check += string;
            }
        }
        return Check;
    }

    private void setDoubleOrInt() {
        DecimalFormat df = new DecimalFormat("###.####");
        float f = 20.0f;
        System.out.println("你不想要的：" + f);//20.0
        System.out.println("你想要的答案：" + df.format(f));//20
    }

    //获取当前时间的4种方法
    private void setDateTime() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("------->今天是：" + df.format(day));

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("------->今天是：" + df2.format(System.currentTimeMillis()));

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println("------->今天是：" + year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second);

        Date date2 = new Date();
        String year2 = String.format("%tY", date2);
        String month2 = String.format("%tB", date2);
        String day2 = String.format("%te", date2);
        System.out.println("------->今天是：" + year2 + "-" + month2 + "-" + day2);
    }

    //
    private void setNetTime() {
        URL url = null;//取得资源对象
        try {
//            url = new URL("http://www.baidu.com");
            url = new URL("http://www.ntsc.ac.cn");//中国科学院国家授时中心
            //url = new URL("http://www.bjtime.cn");
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ld);
            final String format = formatter.format(calendar.getTime());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("------->当前网络时间为:" + format);
                    setTimeStr(format);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String setTimeStr(String time) {
        String timestr = time.replace("-", "");
        timestr = timestr.replace(":", "");
        timestr = timestr.replace(" ", "_");
        System.out.println("------->当前网络时间为:" + timestr);
        return timestr;
    }

    private void testLong() {
        String strA = "500";
        String strB = "30.08";
        String strAdd = ExactComputationByLong.add(strA, strB);
        System.out.println("------>Add:" + strA + "+" + strB + "=" + strAdd);
        String strSub = ExactComputationByLong.subtract(strA, strB);
        System.out.println("------>Sub:" + strA + "-" + strB + "=" + strSub);
        String strMult = ExactComputationByLong.multiply(strA, strB);
        System.out.println("------>Mult:" + strA + "*" + strB + "=" + strMult);
        String sttDiv = ExactComputationByLong.divide(strA, strB);
        System.out.println("------>Div:" + strA + "/" + strB + "=" + sttDiv);
    }

    private static Boolean isExit = false;// 退出

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // finish();
            // System.exit(0);
            Timer tExit = null;
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                    }
                }, 2000);
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    //通知
    private void getChannelNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, ScrollActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                .setContentTitle(title)
                .setContentText(message)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)//优先级高
                .setAutoCancel(true)//点击后消失
                .setContentIntent(pendingIntent)//设置意图
                .build();//创建通知对象完成
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);//显示通知
    }

}
