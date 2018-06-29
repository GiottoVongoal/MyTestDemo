package com.example.giotto.mttext.demo.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.foodmenu.mode.ShopProduct;

import java.util.ArrayList;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.foodmenu.view
 * @date 2017-02-27 11:14
 * @description
 */
public class FoodResultActivity extends Activity {
    private double sum = 0;
    private int shopNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_result_layout);

        Intent intent = getIntent();
        /* ******** 取集合 ******** */
        ArrayList<ShopProduct> list = intent.getParcelableArrayListExtra("devices");

        //使用代码添加TextView
        LinearLayout layout = (LinearLayout) findViewById(R.id.food_result_ll);
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.addView(tv, lp);
            ShopProduct shop = list.get(i);
            tv.setText(shop.getGoods() + "\t\t\t" + "¥" + shop.getPrice() + "/份" + "\t\t\t" + shop.getNumber() + "份" + "\t");
            DoubleUtil.sub((double) shop.getNumber(), Double.parseDouble(shop.getPrice()));
            shopNum += DoubleUtil.sum(sum, DoubleUtil.mul((double) shop.getNumber(), Double.parseDouble(shop.getPrice())));
        }
        TextView tv_money_sum = new TextView(this);
        tv_money_sum.setPadding(10, 10, 10, 10);
        LinearLayout.LayoutParams lp_sum = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(tv_money_sum, lp_sum);
        tv_money_sum.setText("¥\t" + shopNum);
    }
}
