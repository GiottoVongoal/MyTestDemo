package com.example.giotto.mttext.demo.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.giotto.mttext.demo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.lottie
 * @date 2017-05-25 11:25
 * @description
 */
public class LottieTestActivity extends Activity implements View.OnClickListener {
    private LottieAnimationView animation_view;
    private TextView lottie_tv;

    private String giftDir = Environment.getExternalStorageDirectory().getPath();
    private String name = "";

    private TextView lottie_tv1, lottie_tv2, lottie_tv3, lottie_tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottie_layout);

        animation_view = (LottieAnimationView) findViewById(R.id.animation_view);
        lottie_tv = (TextView) findViewById(R.id.lottie_tv);

        lottie_tv1 = (TextView) findViewById(R.id.lottie_tv1);
        lottie_tv1.setText("hello-world");
        lottie_tv2 = (TextView) findViewById(R.id.lottie_tv2);
        lottie_tv2.setText("Spider Loader");
        lottie_tv3 = (TextView) findViewById(R.id.lottie_tv3);
        lottie_tv3.setText("TwitterHeart");
        lottie_tv4 = (TextView) findViewById(R.id.lottie_tv4);
        lottie_tv4.setText("PinJump");

        lottie_tv1.setOnClickListener(this);
        lottie_tv2.setOnClickListener(this);
        lottie_tv3.setOnClickListener(this);
        lottie_tv4.setOnClickListener(this);

//        animation_view.setAnimation("hello-world.json");
//        name = getIntent().getStringExtra(LottieTestActivity.class.getSimpleName());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottie_tv1:
                showLocalJson("hello-world.json");
                break;
            case R.id.lottie_tv2:
                showLocalJson("Spider Loader.json");
                break;
            case R.id.lottie_tv3:
                showLocalJson("TwitterHeart.json");
                break;
            case R.id.lottie_tv4:
                showLocalJson("PinJump.json");
                break;
        }

    }

    /**
     * Lottie显示资源文件中的json
     */
    private void showLottieJson(String name) {
        animation_view.setVisibility(View.VISIBLE);
        animation_view.setAnimation(name);
        animation_view.loop(false);
        animation_view.playAnimation();
    }

    /**
     * Lottie显示手机文件中的json
     */
    private void showLocalJson(String jsonName) {
        File jsonFile = new File(giftDir + "/lottiejson/", jsonName);
//        File imagesDir = new File(giftDir, "lottieimage");
        FileInputStream fis = null;
        if (jsonFile.exists()) {
            try {
                fis = new FileInputStream(jsonFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
//        if (!imagesDir.exists()) {//文件夹不存在
//            imagesDir.mkdir();//创建
//        }
//        if (fis == null || !imagesDir.exists()) {
        if (fis == null) {
            Toast.makeText(this, "啥也没有", Toast.LENGTH_SHORT).show();
//            showLocalAnimation(gift);
            return;
        }
//        final String absolutePath = imagesDir.getAbsolutePath();
//        //提供一个代理接口从 SD 卡读取 images 下的图片
//        animation_view.setImageAssetDelegate(new ImageAssetDelegate() {
//            @Override
//            public Bitmap fetchBitmap(LottieImageAsset asset) {
//                BitmapFactory.Options opts = new BitmapFactory.Options();
//                opts.inScaled = true;
//                opts.inDensity = 160;
//                return BitmapFactory.decodeFile(absolutePath + File.separator +
//                        asset.getFileName(), opts);
//            }
//        });
        //从文件流中加载 json 数据
        LottieComposition.Factory.fromInputStream(this, fis, new OnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view.setComposition(composition);
                //是否循环播放
                animation_view.loop(false);
                //开始播放
                animation_view.playAnimation();
            }
        });
        animation_view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                //开始
                lottie_tv.setVisibility(View.VISIBLE);
                animation_view.setVisibility(View.VISIBLE);
                lottie_tv.setText("送礼物了");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //结束
                lottie_tv.setVisibility(View.GONE);
                animation_view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                //取消
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                //重复
            }
        });
    }
}
