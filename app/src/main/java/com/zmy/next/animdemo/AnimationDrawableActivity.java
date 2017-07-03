package com.zmy.next.animdemo;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AnimationDrawableActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView hello;
    private View animationDrawable;

    private int[] resIds;
    private Drawable[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);

        initView();

        initData();
    }

    private void initView() {
        hello = (ImageView) findViewById(R.id.hello);
        animationDrawable = findViewById(R.id.animationDrawable);
        animationDrawable.setOnClickListener(this);
    }

    private void initData() {
        resIds = getData(R.array.big_praise);
        drawables = new Drawable[resIds.length];
        for (int i = 0; i < resIds.length; i++) {
            drawables[i] = getResources().getDrawable(resIds[i]);
        }
    }

    private int[] getData(int resId) {
        TypedArray array = getResources().obtainTypedArray(resId);

        int len = array.length();
        int[] intArray = new int[array.length()];

        for (int i = 0; i < len; i++) {
            intArray[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        return intArray;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animationDrawable:
                startAnimationDrawable();
                break;
        }
    }

    private void startAnimationDrawable() {
        AnimationDrawable frameAnim = new AnimationDrawable();
        for (Drawable frame : drawables) {
            frameAnim.addFrame(frame, 50);
        }
        frameAnim.setOneShot(true);
        hello.setImageDrawable(frameAnim);

        frameAnim.start();
    }
}
