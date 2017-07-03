package com.zmy.next.animdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1;
    private PointF pointF2;

    public BezierEvaluator(PointF pointF1, PointF pointF2){
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float time, PointF startValue,
                           PointF endValue) {

        final float t = time;
        float oneMinusT = 1.0f - t;

        float p0 = oneMinusT * oneMinusT * oneMinusT;
        float p1 = oneMinusT * oneMinusT * t;
        float p2 = oneMinusT * t;
        float p3 = t * t * t;

        PointF point = new PointF();
        point.x = p0 * (startValue.x)
                + 3 * p1 * (pointF1.x)
                + 3 * p2 * (pointF2.x)
                + p3 * (endValue.x);

        point.y = p0 * (startValue.y)
                + 3 * p1 * (pointF1.y)
                + 3 * p2 * (pointF2.y)
                + p3 * (endValue.y);

        return point;
    }
}
