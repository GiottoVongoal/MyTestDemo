package com.example.giotto.mttext.demo.smarttab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giotto.mttext.demo.R;

/**
 * @Author yly
 * @Data 2018/5/9 15:34
 * @PackageName com.example.giotto.mttext.demo.smarttab
 * @Description  
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myrecyler_add_scrollview, container, false);
        return view;
    }
}
