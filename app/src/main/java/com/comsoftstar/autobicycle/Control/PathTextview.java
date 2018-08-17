package com.comsoftstar.autobicycle.Control;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/17.
 */

public class PathTextview extends View {
    private int DEFAULT_COLOR = Color.WHITE;                 //默认颜色
    private float DEFAULT_WIDTH = 8.0f;                       //默认宽度
    private Object mSvgLock = new Object();
    private String mText = "Kotlin";
    private ArrayList<float[]> mDatas;
    // private  ArrayList<FloatArray> mDatas: ArrayList<FloatArray>? = null;       //
    private ArrayList<Path> mPaths = new ArrayList<>();
    private Paint mPaint = new Paint();
    private ObjectAnimator mSvgAnimator;//=new ObjectAnimator? = null;

    public PathTextview(Context context) {
        super(context);
        init();
    }

    public PathTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private void init() {
//初始化画笔
        mPaint.setStyle(Paint.Style.STROKE);                      //描边  fill填充
        mPaint.setPathEffect(new CornerPathEffect(4f));       //路径效果 圆角4
        mPaint.setAntiAlias(true);                               //.isAntiAlias = false
        mPaint.setStrokeCap(Paint.Cap.ROUND);                       //笔刷图形样式

        mPaint.setColor(DEFAULT_COLOR);                          //画笔颜色
        mPaint.setStrokeWidth(DEFAULT_WIDTH);                       //画笔宽度
    }

    public void init(String text) {
        if (text == null || text.length() == 0) {
            return;
        }

        requestLayout();
        invalidate();

        mText = text;
        //mDatas = MatchPath.getPath(mText);
        //    mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", 0.0f, 1.0f).setDuration(2000)
        //   mSvgAnimator.start();
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) (Float.valueOf(mText.length()) * 72f * 1.0f + Float.valueOf(getPaddingLeft())
                    + Float.valueOf(getPaddingRight()));
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (72.0f * 1) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
