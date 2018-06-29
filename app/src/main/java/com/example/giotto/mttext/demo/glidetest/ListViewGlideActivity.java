package com.example.giotto.mttext.demo.glidetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.giotto.mttext.demo.R;


/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.glidetest
 * @date 2016-09-22 12:55
 * @description
 */
public class ListViewGlideActivity extends Activity {
    private ListView usage_example_lv;
    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };
    private ImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_glide_layout);
        init();
    }

    private void init() {
        usage_example_lv = (ListView) findViewById(R.id.usage_example_lv);
        adapter = new ImageListAdapter(ListViewGlideActivity.this, eatFoodyImages);
        usage_example_lv.setAdapter(adapter);
    }
}
