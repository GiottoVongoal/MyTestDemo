package com.example.giotto.mttext.demo.bannar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.bannar.xbanner.XBanner;
import com.example.giotto.mttext.demo.glidetest.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yly
 * @Data 2019/1/3 16:09
 * @Description  
 */
public class BannarActivity extends Activity {
    private XBanner mXBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannar);

        final List<String> imgList = new ArrayList<>();
        imgList.add("https://b-ssl.duitang.com/uploads/blog/201509/29/20150929164702_KMUBn.thumb.700_0.jpeg");
        imgList.add("https://b-ssl.duitang.com/uploads/item/201806/26/20180626164817_uEJXi.thumb.700_0.jpeg");
        imgList.add("https://b-ssl.duitang.com/uploads/item/201301/26/20130126080742_834LW.thumb.700_0.jpeg");


        //获取控件
        mXBanner = (XBanner) findViewById(R.id.xbanner);
        //添加轮播图片数据（图片数据不局限于网络图片、本地资源文件、View 都可以）,刷新数据也是调用该方法
        mXBanner.setData(imgList, null);
        //加载广告图片
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                GlideImgManager.loadRoundCornerImage(BannarActivity.this, imgList.get(position), (ImageView) view);
            }
        });
        mXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(BannarActivity.this, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }
}