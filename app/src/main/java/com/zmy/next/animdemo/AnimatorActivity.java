package com.zmy.next.animdemo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private View hello;
    private View objectAnimator, valueAnimator, animatorSet;
    private ImageView imageView;
    private FrameLayout tb_layout;

    AnimatorSet setRightOut;
    AnimatorSet setLeftIn;
    AnimatorSet setLeftOut;
    AnimatorSet setRightIn;

    private boolean toImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        initView();
    }

    private void initView() {
        hello = findViewById(R.id.hello);

        objectAnimator = findViewById(R.id.objectAnimator);
        valueAnimator = findViewById(R.id.valueAnimator);
        animatorSet = findViewById(R.id.animatorSet);

        imageView = (ImageView) findViewById(R.id.image);
        tb_layout = (FrameLayout) findViewById(R.id.tb_layout);

        objectAnimator.setOnClickListener(this);
        valueAnimator.setOnClickListener(this);
        animatorSet.setOnClickListener(this);

        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.rotate_right_out);

        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.rotate_left_in);

        setLeftOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.rotate_left_out);

        setRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.rotate_right_in);

        tb_layout.setAlpha(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.objectAnimator:
                startObjectAnimator();
                break;
            case R.id.valueAnimator:
                startValueAnimator();
                break;
            case R.id.animatorSet:
                startAnimatorSet();
                break;
            default:
                break;
        }
    }

    private void startAnimatorSet() {
        toImage = !toImage;
        if (toImage) {
            setLeftIn.setTarget(imageView);
            setRightOut.setTarget(tb_layout);

            setLeftIn.start();
            setRightOut.start();
        } else {
            setLeftOut.setTarget(imageView);
            setRightIn.setTarget(tb_layout);

            setLeftOut.start();
            setRightIn.start();
        }
    }

    private void startValueAnimator() {

        int colorStart = Color.parseColor("#ccffff");
        int colorEnd = Color.parseColor("#ffffcc");
        ValueAnimator animator = ValueAnimator.ofInt(colorStart, colorEnd);
        animator.setTarget(hello);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                hello.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();

    }

    private void startObjectAnimator() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("alpha", 1, 0.3f, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1, 3.2f, 3);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1, 3.2f, 3);

        int colorStart = Color.parseColor("#ccffff");
        int colorEnd = Color.parseColor("#ffffcc");
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofInt("backgroundColor", colorStart, colorEnd);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(hello, holder, holder2, holder3);
        animator.setDuration(500);
        animator.start();
    }
}
