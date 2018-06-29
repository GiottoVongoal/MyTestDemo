package com.example.giotto.mttext.demo.materialdesign;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-06-12 19:29
 * @description view1可跟随手指移动且有阴影;view2增加控件阴影效果
 */
public class MaterialDesignBasicActivity extends Activity {
    private View material_design_basic_view2;
    private TextView material_design_basic_view1;

    private ViewOutlineProvider mOutlineProviderCircle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_basic_layout);

        material_design_basic_view2 = (View) findViewById(R.id.material_design_basic_view2);
        material_design_basic_view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.setTranslationZ(200);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setTranslationZ(0);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        material_design_basic_view1 = (TextView) findViewById(R.id.material_design_basic_view1);
        mOutlineProviderCircle = new CircleOutlineProvider();
        material_design_basic_view1.setOutlineProvider(mOutlineProviderCircle);
        material_design_basic_view1.setClipToOutline(true);//可以剪切
        DragFrameLayout dragLayout = (DragFrameLayout) findViewById(R.id.material_design_basic_drag_layout);
        dragLayout.setDragFrameController(new DragFrameLayout.DragFrameLayoutController() {
            @Override
            public void onDragDrop(boolean captured) {
                //  通过动画的方式让视图的阴影增大和减小
                material_design_basic_view1.animate().translationZ(captured ? 50 : 0).setDuration(100);
                //参数captured为true代表按下、false代表拿起。
            }
        });
        dragLayout.addDragView(material_design_basic_view1);
    }


    private class CircleOutlineProvider extends ViewOutlineProvider {
        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, view.getWidth(), view.getHeight());

        }
    }
}
