package com.example.giotto.mttext.demo.glidetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.giotto.mttext.demo.R;

import java.util.HashMap;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.glidetest
 * @date 2016-09-22 12:58
 * @description
 */
public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private final HashMap<Integer, View> viewHolderMap = new HashMap<Integer, View>();
    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public Object getItem(int i) {
        return imageUrls[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (viewHolderMap.get(position) == null) {
            convertView = LinearLayout.inflate(context, R.layout.listview_item_image, null);
            viewHolder = new ViewHolder();
            // 文字部分
            viewHolder.lv_iv = (ImageView) convertView.findViewById(R.id.lv_iv);
            convertView.setTag(viewHolder);
            viewHolderMap.put(position, convertView);
        } else {
            convertView = viewHolderMap.get(position);
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GlideImgManager.loadImage(context,imageUrls[position],viewHolder.lv_iv);
//        Glide
//                .with(context)
//                .load(imageUrls[position])
//                .into(viewHolder.lv_iv);
        return convertView;
    }

    private class ViewHolder {
        public ImageView lv_iv;
    }
}
