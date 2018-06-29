package com.example.giotto.mttext.demo.lottie;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.giotto.mttext.demo.R;

import butterknife.ButterKnife;

public class PageTwoFragment extends Fragment {
    public static final String ARG_PAGE = "MESSAGE";
    LottieAnimationView animationView;
    Button start;
    Button stop;
    private int animationPosition = 0;

    private String mPage;
    private String[] JsonList = new String[]{"Mobilo/A.json", "Mobilo/B.json", "Mobilo/C.json", "Mobilo/D.json",
            "Mobilo/E.json", "Mobilo/F.json", "Mobilo/G.json", "Mobilo/F.json", "Mobilo/I.json", "Mobilo/J.json", "Mobilo/K.json",
            "Mobilo/L.json", "Mobilo/M.json", "Mobilo/N.json", "Mobilo/O.json", "Mobilo/P.json", "Mobilo/Q.json", "Mobilo/R.json",
            "Mobilo/S.json", "Mobilo/T.json", "Mobilo/U.json", "Mobilo/V.json", "Mobilo/W.json", "Mobilo/X.json", "Mobilo/Y.json", "Mobilo/Z.json"};

    public static PageTwoFragment newInstance(String message) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, message);
        PageTwoFragment pageFragment = new PageTwoFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getString(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.lottie_layout, null);

        animationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
//        start = (Button) view.findViewById(R.id.start);
//        stop = (Button) view.findViewById(R.id.stop);

        initListener();
        return view;
    }

    private void initListener() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.removeAnimatorListener(myAnimatorListener);
                animationPosition = 0;
                startAnimation();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationPosition = JsonList.length + 1;
                animationView.cancelAnimation();
            }
        });
    }

    /**
     * 播放动画
     */
    private void startAnimation() {
        animationView.setProgress(0f);
        //添加播放源
        animationView.setAnimation(JsonList[animationPosition]);
        //是否循环播放
        animationView.loop(false);
        //开始播放
        animationView.playAnimation();
        animationView.addAnimatorListener(myAnimatorListener);
    }

    private Animator.AnimatorListener myAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            Log.e("huangxiaoguo", "onAnimationStart");
            //开始
            animationPosition = animationPosition + 1;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            Log.e("huangxiaoguo", "onAnimationEnd");
            //结束
            if (animationPosition < JsonList.length) {
                //添加播放源
                animationView.setAnimation(JsonList[animationPosition]);
                //是否循环播放
                animationView.loop(false);
                //开始播放
                animationView.playAnimation();
            } else if (animationPosition == JsonList.length) {
                animationPosition = 0;
                //添加播放源
                animationView.setAnimation(JsonList[animationPosition]);
                //是否循环播放
                animationView.loop(false);
                //开始播放
                animationView.playAnimation();
            }

        }

        @Override
        public void onAnimationCancel(Animator animator) {
            Log.e("huangxiaoguo", "onAnimationCancel");
            //取消
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            Log.e("huangxiaoguo", "onAnimationRepeat");
            //重复
        }
    };

    /**
     * 停止动画
     */
    @Override
    public void onPause() {
        super.onPause();
        animationPosition = JsonList.length + 1;
        animationView.cancelAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
