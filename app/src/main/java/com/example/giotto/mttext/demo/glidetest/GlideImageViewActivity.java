package com.example.giotto.mttext.demo.glidetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.glidetest
 * @date 2016-09-22 11:20
 * @description
 */
public class GlideImageViewActivity extends Activity {
    @BindView(R.id.imageview1)
    ImageView imageview1;
    @BindView(R.id.imageview2)
    ImageView imageview2;
    @BindView(R.id.imageview3)
    ImageView imageview3;
    @BindView(R.id.imageview4)
    ImageView imageview4;
    @BindView(R.id.imageview5)
    ImageView imageview5;
    @BindView(R.id.textview1)
    Button textview1;
    @BindView(R.id.imageview6)
    ImageView imageview6;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String imageArr[] = {"http://pic14.nipic.com/20110607/6776092_111031284000_2.jpg",
            "http://cdn.duitang.com/uploads/item/201507/13/20150713235634_UKdLB.jpeg",
            "http://att2.citysbs.com/taizhou/2011/08/27/101937_kuumaaio_40ee6a85b8df443965c4ca5e6f5d80fa.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //加载网络图片（普通）
        GlideImgManager.loadImage(this, imageArr[0], imageview1);

        //加载网络图片（圆角）
        GlideImgManager.loadRoundCornerImage(this, imageArr[1], imageview2);

        //加载网络图片（圆形）
        GlideImgManager.loadCircleImage(this, imageArr[2], imageview3);

        //加载项目中的图片
        GlideImgManager.loadImage(this, R.mipmap.deadpool, imageview4);

        //加载网络图片（GIF）
        String gifUrl = "http://ww4.sinaimg.cn/mw690/bcc93f49gw1f6r1nce77jg207x07sx6q.gif";
        GlideImgManager.loadGifImage(this, gifUrl, imageview5);

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.intentStartActivity(GlideImageViewActivity.this, ListViewGlideActivity.class);
            }
        });
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListener();
            }
        });
    }

    //加载进度监听
    private void loadListener() {
        Glide.with(this)
                .load(imageArr[0])
                .into(new GlideDrawableImageViewTarget(imageview6) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
    }
}