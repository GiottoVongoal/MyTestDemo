package com.example.giotto.mttext.demo.timecount;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.timecount
 * @date 2017-02-17 14:02
 * @description
 */
public class GetVerificationCodeActivity extends Activity implements View.OnClickListener {
    private EditText et_phone;//电话输入框
    private ImageView iv_delete_phone;//电话一键删除

    private EditText et_code;//验证码输入框
    private ImageView iv_delete_code;//验证码一键删除

    private TextView tv_getcode;//获取验证码按钮

    private TextView tv_next;//下一步按钮

    private int grey = R.color.grey9;
    private int blue = R.color.blue3;
    private TimeCount time;

    private CircleTextView circle_view_tv;//测试TextView显示文字,背景为一个圆形

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_verification_code_layout);
        init();
        //
        circle_view_tv = (CircleTextView) findViewById(R.id.circle_view_tv);
        circle_view_tv.setBackgroundColor(Color.WHITE);
        circle_view_tv.setText("商标审核中");
    }

    private void init() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        iv_delete_phone = (ImageView) findViewById(R.id.iv_delete_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        iv_delete_code = (ImageView) findViewById(R.id.iv_delete_code);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        tv_next = (TextView) findViewById(R.id.tv_next);
        //初始化短信倒计时内容
        //time = new TimeCount(60000, 100);// 构造CountDownTimer对象
        time = new TimeCount(6000, 100);// 构造CountDownTimer对象
        time.init(this, tv_getcode);
        MyUtils.setEdittext(this, et_phone, new EditText[]{et_phone, et_code},
                iv_delete_phone, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_phone.setText("");
                    }
                }, tv_next, grey, blue);
        MyUtils.setEdittext(this, et_code, new EditText[]{et_phone, et_code},
                iv_delete_code, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_code.setText("");
                    }
                }, tv_next, grey, blue);
        MyUtils.setPhoneIstrueEditText(this, et_phone,
                iv_delete_phone, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_phone.setText("");
                    }
                }, tv_getcode, grey, blue);
        tv_getcode.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getcode:
                if (MyUtils.checkPhoneNum(et_phone.getText().toString())) {
                    time.start();// 开始计时
                }
                break;
            case R.id.tv_next:
                String phone = et_phone.getText().toString();
                String code = et_code.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
                    MyUtils.showToast(GetVerificationCodeActivity.this, "下一步");
                }
                break;
        }
    }
}
