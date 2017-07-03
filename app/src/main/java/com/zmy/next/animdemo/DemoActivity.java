package com.zmy.next.animdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by zmy on 2017/7/3.
 */

public class DemoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView, imageView2;
    private Button button, button2;
    private FrameLayout bg_alpha;
    private boolean isOpen, isOpen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image_view);
        button = (Button) findViewById(R.id.button);
        bg_alpha = (FrameLayout) findViewById(R.id.bg_alpha);

        imageView2 = (ImageView) findViewById(R.id.image_view2);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(this);
        imageView.setOnClickListener(this);

        button2.setOnClickListener(this);
        imageView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
            case R.id.image_view:
                isOpen = !isOpen;
                if (isOpen) {
                    openImageAnim();
                } else {
                    closeImageAnim();
                }
                break;

            case R.id.button2:
            case R.id.image_view2:
                isOpen2 = !isOpen2;
                if (isOpen2) {
                    openImageAnim2();
                } else {
                    closeImageAnim2();
                }
                break;
        }
    }

    private void openImageAnim2() {
        //image
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("alpha", 0, 0.3f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView2, holder4);

        BezierEvaluator evaluator = new BezierEvaluator(new PointF(0.3f * 0.4f, 0.3f * 0.4f), new PointF(1.22f, 1.22f));

        ValueAnimator animator3 = ValueAnimator.ofObject(evaluator, new PointF(0.3f, 0.3f), new PointF(1f, 1f));
        animator3.addUpdateListener(new DemoActivity.BezierListener(imageView2));

        bg_alpha.setBackgroundColor(Color.parseColor("#35000000"));
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(bg_alpha, "alpha", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(animator, animator2, animator3);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    private void closeImageAnim2() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.3f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.3f);

        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView2, holder, holder2, holder4);

        animator.setInterpolator(new AnticipateOvershootInterpolator());

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(bg_alpha, "alpha", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(animator, animator2);
        animatorSet.start();
    }


    private void openImageAnim() {
        //image

        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("alpha", 0, 0.3f, 1f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder, holder2, holder4);

        animator.setInterpolator(new OvershootInterpolator());

        int colorStart = Color.parseColor("#00000000");
        int colorEnd = Color.parseColor("#35000000");
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofInt("backgroundColor", colorStart, colorEnd);

        bg_alpha.setBackgroundColor(Color.parseColor("#00000000"));
        bg_alpha.setAlpha(1f);

        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(bg_alpha, holder3);
        animator2.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSetInner = new AnimatorSet();
        animatorSetInner.playTogether(animator, animator2);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(animatorSetInner);
        animatorSet.start();
    }

    private void closeImageAnim() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.3f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.3f);

        PropertyValuesHolder holder4 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder, holder2, holder4);

        animator.setInterpolator(new AnticipateOvershootInterpolator());

        int colorEnd = Color.parseColor("#00000000");
        int colorStart = Color.parseColor("#35000000");

        PropertyValuesHolder holder3 = PropertyValuesHolder.ofInt("backgroundColor", colorStart, colorEnd);

        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(bg_alpha, holder3);
        animator2.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(animator, animator2);
        animatorSet.start();
    }


    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setScaleX(pointF.x);
            target.setScaleY(pointF.y);
//            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

}
