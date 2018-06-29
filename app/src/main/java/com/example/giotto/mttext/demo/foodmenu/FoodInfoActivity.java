package com.example.giotto.mttext.demo.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;


public class FoodInfoActivity extends Activity {
    private int id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_info_layout);
        init();
        showFoodInfo();
    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
    }

    private void showFoodInfo() {
        //使用代码添加TextView
        LinearLayout layout = (LinearLayout) findViewById(R.id.food_info_ll);
        TextView tv = new TextView(this);
        tv.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv, lp);
        tv.setText(name + "\t" + id + "");
    }
}
