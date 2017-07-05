package com.zmy.next.animdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button animation, animationDrawable, animator, materialDesign, demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        animation = (Button) findViewById(R.id.animation);
        animationDrawable = (Button) findViewById(R.id.animationDrawable);
        animator = (Button) findViewById(R.id.animator);
        materialDesign = (Button) findViewById(R.id.materialDesign);
        demo = (Button) findViewById(R.id.demo);

        animation.setOnClickListener(this);
        animationDrawable.setOnClickListener(this);
        animator.setOnClickListener(this);
        materialDesign.setOnClickListener(this);
        demo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.animationDrawable:
                startActivity(new Intent(MainActivity.this, AnimationDrawableActivity.class));
                break;
            case R.id.animator:
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
                break;
            case R.id.materialDesign:
                startActivity(new Intent(MainActivity.this, MaterialDesignActivity.class));
                break;
            case R.id.demo:
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
                break;
        }
    }
}
