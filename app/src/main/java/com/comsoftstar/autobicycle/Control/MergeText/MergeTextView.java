package com.comsoftstar.autobicycle.Control.MergeText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.comsoftstar.autobicycle.R;


/**
 * Created by Administrator on 2017/12/14.
 */

public class MergeTextView extends View {
    private DisplayUtils displayUtils;

    private Paint leftTxtPaint;

private String leftTxt = "100";
    private int leftTxtSize;
    private int leftTxtColor;

    private Paint rightTxtPaint;
    private String rightTxt = "%";
    private int rightTxtSize;

    public String getLeftTxt() {
        return leftTxt;
    }

    public void setLeftTxt(String leftTxt) {
        this.leftTxt = leftTxt;
        initPaint();
        postInvalidate();
    }

    public int getLeftTxtSize() {
        return leftTxtSize;
    }

    public void setLeftTxtSize(int leftTxtSize) {
        this.leftTxtSize = leftTxtSize;
        initPaint();
        postInvalidate();
    }

    public int getLeftTxtColor() {
        return leftTxtColor;
    }

    public void setLeftTxtColor(int leftTxtColor) {
        this.leftTxtColor = leftTxtColor;
        initPaint();
        postInvalidate();
    }

    public String getRightTxt() {
        return rightTxt;
    }

    public void setRightTxt(String rightTxt) {
        this.rightTxt = rightTxt;
    }

    public int getRightTxtSize() {
        return rightTxtSize;
    }

    public void setRightTxtSize(int rightTxtSize) {
        this.rightTxtSize = rightTxtSize;
        initPaint();
        postInvalidate();
    }

    public int getRightTxtColor() {
        return rightTxtColor;
    }

    public void setRightTxtColor(int rightTxtColor) {
        this.rightTxtColor = rightTxtColor;
        initPaint();
        postInvalidate();
    }

    public float getSpace4LAndR() {
        return space4LAndR;
    }

    public void setSpace4LAndR(float space4LAndR) {
        this.space4LAndR = space4LAndR;
        postInvalidate();
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        postInvalidate();
    }

    private int rightTxtColor;
    private float measureLeftText;
    private float measureRightText;
    private float measuredHeight;
    private float measuredWidth;
    private float space4LAndR;
    private float leftStartX;
    private float leftStartY;
    private float rightStartX;
    private float rightStartY;
    private int gravity;
    private final int CENTER = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;

    public MergeTextView(Context context) {
        this(context,null);
    }

    public MergeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MergeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if(displayUtils==null) displayUtils = new DisplayUtils();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MergeTextView);
        leftTxt = ta.getString(R.styleable.MergeTextView_leftTxt);
        leftTxtColor = ta.getColor(R.styleable.MergeTextView_leftTxtColor, Color.BLACK);
        leftTxtSize = ta.getDimensionPixelSize(R.styleable.MergeTextView_leftTxtSize,displayUtils.dip2px(20.0f,context));

        rightTxt = ta.getString(R.styleable.MergeTextView_rightTxt);
        rightTxtColor = ta.getColor(R.styleable.MergeTextView_rightTxtColor, Color.RED);
        rightTxtSize = ta.getDimensionPixelSize(R.styleable.MergeTextView_rightTxtSize,displayUtils.dip2px(8.0f,context));

        space4LAndR = ta.getDimensionPixelOffset(R.styleable.MergeTextView_space4LAndR,displayUtils.dip2px(2.0f,context));
        gravity = ta.getInt(R.styleable.MergeTextView_gravity,CENTER);
        ta.recycle();
    }

    private void initPaint() {
        //左边字
        if(leftTxtPaint==null) leftTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            leftTxtPaint.setStyle(Paint.Style.FILL);
            leftTxtPaint.setTextSize(leftTxtSize);
            leftTxtPaint.setColor(leftTxtColor);
            if (leftTxt!=null) {
                measureLeftText = leftTxtPaint.measureText(leftTxt);
            }


        //右边字
        if(rightTxtPaint==null) rightTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rightTxtPaint.setStyle(Paint.Style.FILL);
            rightTxtPaint.setTextSize(rightTxtSize);
            rightTxtPaint.setColor(rightTxtColor);
        measureRightText = rightTxtPaint.measureText(rightTxt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measuredWidth = measureLeftText+space4LAndR+measureRightText+getPaddingRight()+getPaddingLeft();
        measuredHeight = leftTxtPaint.getTextSize()+getPaddingTop()+getPaddingBottom();

        // 当布局参数设置为wrap_content时，设置默认值
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension((int)measuredWidth,(int)measuredHeight);
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension((int)measuredWidth, heightSize);
            measuredHeight = getMeasuredHeight();
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, (int)measuredHeight);
            measuredWidth = getMeasuredWidth();
        }else {
            measuredHeight = getMeasuredHeight();
            measuredWidth = getMeasuredWidth();
        }
//        heightWithPadding = measuredHeight - getPaddingTop() - getPaddingBottom();
//        widthWithPadding = measuredWidth - getPaddingLeft() - getPaddingRight();
        if(gravity==LEFT){
            leftStartX = getPaddingLeft();
            leftStartY = measuredHeight - getPaddingBottom()-leftTxtPaint.descent()-displayUtils.dip2px(2,getContext());
            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==CENTER){
            leftStartX = measuredWidth/2-(measureLeftText+space4LAndR+measureRightText)/2;
            leftStartY = measuredHeight/2+leftTxtPaint.getTextSize()/2-displayUtils.dip2px(2,getContext());
//                    + Math.abs(leftTxtPaint.ascent()-leftTxtPaint.descent())/2;
            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==RIGHT){
            leftStartX = measuredWidth -getPaddingRight() - measureLeftText - measureRightText - space4LAndR;
            leftStartY = measuredHeight-getPaddingBottom() - leftTxtPaint.descent()-displayUtils.dip2px(2,getContext());
            rightStartX = leftStartX + measureLeftText + space4LAndR;
            rightStartY = leftStartY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(leftTxt,leftStartX,leftStartY,leftTxtPaint);
        canvas.drawText(rightTxt,rightStartX,rightStartY,rightTxtPaint);
        invalidate();
    }
}
