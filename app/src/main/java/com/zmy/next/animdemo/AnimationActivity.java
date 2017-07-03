package com.zmy.next.animdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private View hello;
    private View alpha, scale, rotate, translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        initView();
    }

    private void initView() {
        hello = findViewById(R.id.hello);
        alpha = findViewById(R.id.alpha);
        scale = findViewById(R.id.scale);
        rotate = findViewById(R.id.rotate);
        translate = findViewById(R.id.translate);

        alpha.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        translate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                startAlphaAnimation();
                break;
            case R.id.scale:
                startScaleAnimation();
                break;
            case R.id.rotate:
                startRotateAnimation();
                break;
            case R.id.translate:
                startTranslateAnimation();
                break;
            default:
                break;
        }
    }

    private void startTranslateAnimation() {
        cancelPrev();

        TranslateAnimation animation = new TranslateAnimation(0, 100, 0, 0);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);

        hello.startAnimation(animation);
    }

    private void startRotateAnimation() {
        cancelPrev();

        RotateAnimation animation = new RotateAnimation(0, 180);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);

        hello.startAnimation(animation);
    }

    private void startScaleAnimation() {
        cancelPrev();

        ScaleAnimation animation = new ScaleAnimation(1, 3, 1, 3);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);

        hello.startAnimation(animation);
    }

    private void startAlphaAnimation() {
        cancelPrev();

        AlphaAnimation animation = new AlphaAnimation(1, 0.3f);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);

        hello.startAnimation(animation);
    }

    private void cancelPrev() {
        if (hello.getAnimation() != null) {
            hello.getAnimation().cancel();
        }
    }
}
