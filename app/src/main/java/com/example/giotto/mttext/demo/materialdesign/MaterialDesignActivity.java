package com.example.giotto.mttext.demo.materialdesign;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-06-12 15:27
 * @description MaterialDesign视觉设计语言
 */
public class MaterialDesignActivity extends AppCompatActivity {
    private NavigationView navigation_view;
    private TextView open_left_drawer_layout_tv;
    private DrawerLayout drawer_layout;

    private TextInputLayout textInputLayout_phone, textInputLayout_name, textInputLayout_password;

    private TextInputLayout xuehao_text_layout;
    private TextInputEditText xuehao_text_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //抽屉滑出为沉浸式
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.material_design_layout);

        Toolbar drawer_toolbar = (Toolbar) findViewById(R.id.drawer_toolbar);
        setSupportActionBar(drawer_toolbar);

        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        open_left_drawer_layout_tv = (TextView) findViewById(R.id.open_left_drawer_layout_tv);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //定义Toolbar左边图片样式和并可点击,对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.car_order_center_settlement_dialog_iv);
        }
        open_left_drawer_layout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
        setNavigationViewItemClickListener();

        //强大的提示控件TextInputLayout使用
        //手机号
        textInputLayout_phone = (TextInputLayout) findViewById(R.id.textInputLayout_phone);
        textInputLayout_phone.getEditText().addTextChangedListener(new MyTextWatcher(textInputLayout_phone, "请输入正确的手机号!", 1));
        //开启计数(输入框后边有0/11的字数统计)
        textInputLayout_phone.setCounterEnabled(true);
        textInputLayout_phone.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/11的字数统计)
        //姓名
        textInputLayout_name = (TextInputLayout) findViewById(R.id.textInputLayout_name);
        textInputLayout_name.getEditText().addTextChangedListener(new MyTextWatcher(textInputLayout_name, "输入长度大于6!", 2));
        //开启计数
        textInputLayout_name.setCounterEnabled(true);//输入框显示输入字数长度
        textInputLayout_name.setCounterMaxLength(6);//最大输入限制数(输入框后边有0/6的字数统计)
        //密码
        textInputLayout_password = (TextInputLayout) findViewById(R.id.textInputLayout_password);
        textInputLayout_password.getEditText().addTextChangedListener(new MyTextWatcher(textInputLayout_password, "输入长度大于6!", 2));
        //开启计数
        textInputLayout_password.setCounterEnabled(true);
        textInputLayout_password.setCounterMaxLength(6);//最大输入限制数(输入框后边有0/6的字数统计)
        //学号
        xuehao_text_layout = (TextInputLayout) findViewById(R.id.xuehao_text_layout);
        xuehao_text_edit = (TextInputEditText) findViewById(R.id.xuehao_text_edit);
        xuehao_text_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 4) {
                    xuehao_text_edit.setError("请输入4位学号", getDrawable(R.mipmap.a));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setNavigationViewItemClickListener() {
        View view = getLayoutInflater().inflate(R.layout.navigation_header, null);
        TextView navigation_header_tv = (TextView) view.findViewById(R.id.navigation_header_tv);
        navigation_header_tv.setText("我是header文字");
        navigation_view.addHeaderView(view);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            MenuItem mPreMenuItem;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        //首页
                        MyUtils.showToast(MaterialDesignActivity.this, "首页");
                        break;
                    case R.id.navigation_item_blog:
                        //我的博客
                        MyUtils.showToast(MaterialDesignActivity.this, "我的博客");
                        break;
                    case R.id.navigation_item_about:
                        //关于
                        MyUtils.showToast(MaterialDesignActivity.this, "关于");
                        break;
                    case R.id.navigation_item_sub_1:
                        //Sub item 1
                        MyUtils.showToast(MaterialDesignActivity.this, "Sub item 1");
                        break;
                    case R.id.navigation_item_sub_2:
                        //Sub item 2
                        MyUtils.showToast(MaterialDesignActivity.this, "Sub item 2");
                        break;
                }
                if (mPreMenuItem != null) mPreMenuItem.setChecked(false);
                item.setChecked(true);
                drawer_layout.closeDrawer(Gravity.LEFT);
                mPreMenuItem = item;
                return true;
            }
        });
        Resources resource = getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.navigation_menu_item_color);
        navigation_view.setItemTextColor(csl);
        //Item图标显示原始颜色
        navigation_view.setItemIconTintList(null);
        // 隐藏滚动条
        NavigationMenuView menuView = (NavigationMenuView) navigation_view.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);
        // 设置MenuItem默认选中项
        navigation_view.getMenu().getItem(0).setChecked(true);
        //头布局增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.showToast(MaterialDesignActivity.this, "headerView");
            }
        });
        navigation_header_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUtils.showToast(MaterialDesignActivity.this, "我是header文字");
            }
        });
    }

    //菜单栏监听
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打开抽屉布局
                drawer_layout.openDrawer(Gravity.START);
                break;
            case R.id.toolbar_menu_backup:
                MyUtils.showToast(this, "backup");
                break;
            case R.id.toolbar_menu_delete:
                MyUtils.showToast(this, "delete");
                break;
            case R.id.toolbar_menu_setting:
                MyUtils.showToast(this, "setting");
                break;
        }
        return true;
    }

    class MyTextWatcher implements TextWatcher {
        private String errorStr;
        private TextInputLayout textInputLayout;
        private int type;

        public MyTextWatcher(TextInputLayout textInputLayout, String errorStr, int type) {
            this.textInputLayout = textInputLayout;
            this.errorStr = errorStr;
            this.type = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (type) {
                case 1://验证手机号
                    if (MyUtils.checkPhoneNum(textInputLayout.getEditText().getText().toString())) {
                        textInputLayout.setErrorEnabled(false);
                    } else {
                        textInputLayout.setErrorEnabled(true);
                        //设置错误提示的信息
                        textInputLayout.setError(errorStr);
                    }
                    break;
                case 2://验证长度不大于6
                    if (textInputLayout.getEditText().getText().toString().length() <= 6) {
                        textInputLayout.setErrorEnabled(false);
                    } else {
                        textInputLayout.setErrorEnabled(true);
                        //设置错误提示的信息
                        textInputLayout.setError(errorStr);
                    }
                    break;
            }
        }
    }
}
