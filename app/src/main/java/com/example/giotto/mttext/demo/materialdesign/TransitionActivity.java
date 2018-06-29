package com.example.giotto.mttext.demo.materialdesign;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class TransitionActivity extends AppCompatActivity {
    private TextView transition_view, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.transition_layout);
        transition_view = (TextView) findViewById(R.id.transition_view);
        transition_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransitionActivity.this, TransitionTwo_Activity.class);
                String transitionName = "square_blue";
                ActivityOptions transitionActivityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(TransitionActivity.this, transition_view, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });

        view2 = (TextView) findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent i = new Intent(TransitionActivity.this, TransitionCircleActivity.class);
                    String transitionName = "square_circle";
                    ActivityOptions transitionActivityOptions =
                            ActivityOptions.makeSceneTransitionAnimation(TransitionActivity.this, view2, transitionName);
                    startActivityForResult(i, 1, transitionActivityOptions.toBundle());
                } else {
                    startActivity(new Intent(TransitionActivity.this, TransitionCircleActivity.class));
                }
            }
        });
    }
}
