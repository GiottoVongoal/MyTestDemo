package com.example.giotto.mttext.demo.picturefullscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.giotto.mttext.demo.R;


/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo
 * @date 2017-02-21 14:38
 * @description
 */
public class PictureFullScreenActivity extends Activity {
    MyListView listView;

    LinearLayout ll_picture_full_screen;

    RelativeLayout relativeLayout;
    ImageView imageView;
    int[] images = {R.mipmap.tanc_00000, R.mipmap.tp04, R.mipmap.tanc_00002, R.mipmap.tp04, R.mipmap.tanc_00002};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_full_screen_layout);
        ll_picture_full_screen = (LinearLayout) findViewById(R.id.ll_picture_full_screen);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_picture_full_screen);
        imageView = (ImageView) findViewById(R.id.iv_picture_full_screen);
        listView = (MyListView) findViewById(R.id.lv_picture_full_screen);
        listView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = PictureFullScreenActivity.this.getLayoutInflater()
                            .inflate(R.layout.picture_full_screen_item, null);
                }
                ImageView imageView = (ImageView) convertView
                        .findViewById(R.id.item_image);
                imageView.setImageResource(images[position]);
                return convertView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return images.length;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                relativeLayout.setVisibility(View.VISIBLE);
                ll_picture_full_screen.setBackgroundResource(R.mipmap.input_icon_identicode);
                listView.setVisibility(View.GONE);
                imageView.setImageResource(images[position]);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
                ll_picture_full_screen.setBackgroundColor(getResources().getColor(R.color.white));
                listView.setVisibility(View.VISIBLE);
            }
        });
    }
}