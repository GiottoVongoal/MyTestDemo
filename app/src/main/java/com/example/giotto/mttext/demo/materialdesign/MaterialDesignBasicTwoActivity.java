package com.example.giotto.mttext.demo.materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.utils.MyUtils;

public class MaterialDesignBasicTwoActivity extends AppCompatActivity {
    private CoordinatorLayout material_basic_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.material_design_basic_two_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MyUtils.showToast(MaterialDesignBasicTwoActivity.this, "Action");
                            }
                        }).show();
            }
        });

        material_basic_rl = (CoordinatorLayout) findViewById(R.id.material_basic_rl);
        material_basic_rl.post(new Runnable() {
            @Override
            public void run() {
                // 圆形动画的x坐标  位于View的中心
                int cx = (material_basic_rl.getLeft() + material_basic_rl.getRight()) / 2;
                //圆形动画的y坐标  位于View的中心
                int cy = (material_basic_rl.getTop() + material_basic_rl.getBottom()) / 2;
                //起始大小半径
                float startX = 0f;
                //结束大小半径 大小为图片对角线的一半
                float startY = (float) Math.sqrt(cx * cx + cy * cy);
                // View view, 指定了为哪个View执行动画(material_basic_rl)
                // int centerX, 涟漪效果的中心x轴位置(cx)
                // int centerY, 涟漪效果的中心y轴位置(cy)
                // float startRadius, 开始的半径(startX)
                // float endRadius, 结束的半径(startY)
                Animator animator = ViewAnimationUtils.createCircularReveal(material_basic_rl, cx, cy, startX, startY);
                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(600);
                animator.start();
            }
        });
    }

    // 退出按钮
    public void backActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            onBackPressed();
        } else {
            defaultBackPressed();
        }
    }

    // 退出事件
    @Override
    public void onBackPressed() {
        // 圆形动画的x坐标  位于View的中心
        int cx = (material_basic_rl.getLeft() + material_basic_rl.getRight()) / 2;
        //圆形动画的y坐标  位于View的中心
        int cy = (material_basic_rl.getTop() + material_basic_rl.getBottom()) / 2;
        //起始大小半径
        float startX = 0f;
        //结束大小半径 大小为图片对角线的一半
        float startY = (float) Math.sqrt(cx * cx + cy * cy);
        // View view, 指定了为哪个View执行动画(material_basic_rl)
        // int centerX, 涟漪效果的中心x轴位置(cx)
        // int centerY, 涟漪效果的中心y轴位置(cy)
        // float startRadius, 开始的半径(startX)
        // float endRadius, 结束的半径(startY)
        Animator animator = ViewAnimationUtils.createCircularReveal(material_basic_rl, cx, cy, startY, startX);
        //在动画开始的地方速率改变比较慢,然后开始加速
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                material_basic_rl.setVisibility(View.GONE);
                defaultBackPressed();
            }
        });
        animator.setDuration(600);
        animator.start();
    }

    // 默认回退
    private void defaultBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
}
