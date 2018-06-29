package com.example.giotto.mttext.demo.donghua.objectanimator;

import android.view.View;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.donghua.objectanimator
 * @date 2017-09-01 10:18
 * @description
 */
public class Test {
    //                showFinishPopupWindow(view);
//                if (isOpen) {
//                    hide(btn_left);
//                    hide(btn_right);
//                    isOpen = false;
//                } else {
//                    show(btn_left, 2, -300);
//                    show(btn_right, 4, -300);
//                    isOpen = true;
//                }
    private final void hide(final View child) {
        child.animate()
                .setDuration(400)
                .translationX(0)
                .translationY(0)
                .alpha(0)
                .start();
    }

    private final void show(final View child, final int position, final int radius) {
        float angleDeg = 180.f;
        int dist = radius;
        switch (position) {
            case 1:
                angleDeg += 0.f;
                break;
            case 2:
                angleDeg += 45.f;
                break;
            case 3:
                angleDeg += 90.f;
                break;
            case 4:
                angleDeg += 135.f;
                break;
            case 5:
                angleDeg += 180.f;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                break;
        }

        final float angleRad = (float) (angleDeg * Math.PI / 180.f);

        final Float x = dist * (float) Math.cos(angleRad);
        final Float y = dist * (float) Math.sin(angleRad);
        child.animate()
                .setDuration(400)
                .translationX(x)
                .translationY(y)
                .alpha(100)
                .start();
    }

}
