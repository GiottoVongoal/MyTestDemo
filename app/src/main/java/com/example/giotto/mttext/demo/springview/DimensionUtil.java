/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.example.giotto.mttext.demo.springview;

import android.content.Context;

public class DimensionUtil {

    public static int dipToPixel(float dp, Context mContext) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        int pixel = (int) (dp * scale + 0.5f);
        return pixel;
    }
}
