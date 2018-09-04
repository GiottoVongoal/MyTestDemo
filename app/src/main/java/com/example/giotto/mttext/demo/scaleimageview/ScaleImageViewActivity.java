package com.example.giotto.mttext.demo.scaleimageview;

import android.app.Activity;
import android.os.Bundle;

import com.example.giotto.mttext.demo.R;

import xyz.zpayh.hdimage.HDImageView;

/**
 * 通过手势随意缩放、移动ImageView图片页面
 */
public class ScaleImageViewActivity extends Activity {
    private HDImageView iv_iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image_view);
        iv_iamge = (HDImageView) findViewById(R.id.iv_iamge);
        String ivPath = "https://b-ssl.duitang.com/uploads/item/201408/06/20140806115856_T2RWJ.thumb.700_0.png";
        iv_iamge.setImageURI(ivPath);
    }
}
