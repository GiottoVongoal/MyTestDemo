package com.example.giotto.mttext.demo.timecount;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-06-05 14:07
 * @description 输入验证码页面
 */
public class VerificationCodeActivity extends Activity implements VerficationCodeView.InputCompleteListener {

    VerficationCodeView code_view;
    TextView code_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verficationcode_layout);
        code_view = (VerficationCodeView) findViewById(R.id.code_view);
        code_tv = (TextView) findViewById(R.id.code_tv);
    }

    @Override
    public void inputComplete() {
        Log.i("验证码是：", code_view.getEditContent());
        //输入完成后的监听

    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete) {
            code_tv.setText("输入验证码表示同意《用户协议》");
            code_tv.setTextColor(Color.BLACK);
        }
    }
}
