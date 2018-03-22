package com.comsoftstar.autobicycle.Control.MergeText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.comsoftstar.autobicycle.R;


/**
 * Created by Administrator on 2017/12/14.
 */

public class MergeTextView extends View {
    private static final String TAG = "MergeTextView";

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
        //initPaint();
        commit();
    }

    public int getLeftTxtSize() {
        return leftTxtSize;
    }

    public void setLeftTxtSize(int leftTxtSize) {
        this.leftTxtSize = leftTxtSize;
        commit();
    }

    public int getLeftTxtColor() {
        return leftTxtColor;
    }

    public void setLeftTxtColor(int leftTxtColor) {
        this.leftTxtColor = leftTxtColor;
        commit();
    }

    public String getRightTxt() {
        return rightTxt;
    }

    public void setRightTxt(String rightTxt) {
        this.rightTxt = rightTxt;
        commit();
    }

    public int getRightTxtSize() {
        return rightTxtSize;
    }

    public void setRightTxtSize(int rightTxtSize) {
        this.rightTxtSize = rightTxtSize;
        commit();
    }

    public int getRightTxtColor() {
        return rightTxtColor;
    }

    public void setRightTxtColor(int rightTxtColor) {
        this.rightTxtColor = rightTxtColor;
        commit();
    }

    public float getSpace4LAndR() {
        return space4LAndR;
    }

    public void setSpace4LAndR(float space4LAndR) {
        this.space4LAndR = space4LAndR;
        commit();
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        commit();
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
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MergeTextView);
        leftTxt = ta.getString(R.styleable.MergeTextView_leftTxt);
        leftTxtColor = ta.getColor(R.styleable.MergeTextView_leftTxtColor, Color.BLACK);
        leftTxtSize = ta.getDimensionPixelSize(R.styleable.MergeTextView_leftTxtSize,20);
        rightTxt = ta.getString(R.styleable.MergeTextView_rightTxt);
        rightTxtColor = ta.getColor(R.styleable.MergeTextView_rightTxtColor, Color.RED);
        rightTxtSize = ta.getDimensionPixelSize(R.styleable.MergeTextView_rightTxtSize,8);
        space4LAndR = ta.getDimensionPixelOffset(R.styleable.MergeTextView_space4LAndR,0);
                gravity = ta.getInt(R.styleable.MergeTextView_gravity,CENTER);
        ta.recycle();
    }
    private void initPaint() {
        //左边字
        if(leftTxtPaint==null) leftTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        leftTxtPaint.setStyle(Paint.Style.FILL);
        leftTxtPaint.setTextSize(leftTxtSize);
        leftTxtPaint.setColor(leftTxtColor);
        if(leftTxt!=null){
            measureLeftText = leftTxtPaint.measureText(leftTxt);

        }else {
            measureLeftText = 0;
        }
        //右边字
        if(rightTxtPaint==null) rightTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rightTxtPaint.setStyle(Paint.Style.FILL);
        rightTxtPaint.setTextSize(rightTxtSize);
        rightTxtPaint.setColor(rightTxtColor);
        if(rightTxt!=null){
            measureRightText = rightTxtPaint.measureText(rightTxt);
        }else {
            measureRightText = 0;
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽-测量规则的模式和大小

        widthMode = MeasureSpec.getMode(widthMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 获取高-测量规则的模式和大小
        heightMode = MeasureSpec.getMode(heightMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measuredWidth = measureLeftText+space4LAndR+measureRightText+getPaddingRight()+getPaddingLeft();
        measuredHeight = leftTxtPaint.getTextSize()+getPaddingTop()+getPaddingBottom();
//        setMeasuredDimension((int)measuredWidth, (int)measuredHeight);
        // 当布局参数设置为wrap_content时//，设置默认值
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                widthSize=(int)measuredWidth ;
                break;
            case MeasureSpec.AT_MOST:
                widthSize=(int)measuredWidth ;
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                heightSize=(int)measuredHeight;
                break;
            case MeasureSpec.AT_MOST:
                heightSize=(int)measuredHeight;
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
        setMeasuredDimension((int)widthSize, (int)heightSize);
        if(gravity==LEFT){
            leftStartX = getPaddingLeft();
            leftStartY = heightSize - getPaddingBottom()-leftTxtPaint.descent()-2;
            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==CENTER){
            leftStartX = widthSize/2-(measureLeftText+space4LAndR+measureRightText)/2;
            leftStartY = heightSize/2+leftTxtPaint.getTextSize()/2-2;

            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==RIGHT){
            leftStartX = widthSize -getPaddingRight() - measureLeftText - measureRightText - space4LAndR;
            leftStartY = heightSize-getPaddingBottom() - leftTxtPaint.descent()-2;
            rightStartX = leftStartX + measureLeftText + space4LAndR;
            rightStartY = leftStartY;
        }
    }
private int widthSize,heightSize,widthMode,heightMode;
    private void getChangeSize() {
        measuredWidth = measureLeftText+space4LAndR+measureRightText+getPaddingRight()+getPaddingLeft();
        measuredHeight = leftTxtPaint.getTextSize()+getPaddingTop()+getPaddingBottom();
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                widthSize=(int)measuredWidth ;
                break;
            case MeasureSpec.AT_MOST:
                widthSize=(int)measuredWidth ;
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                heightSize=(int)measuredHeight;
                break;
            case MeasureSpec.AT_MOST:
                heightSize=(int)measuredHeight;
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
    //    setMeasuredDimension((int)measuredWidth, (int)measuredHeight);
        if(gravity==LEFT){
            leftStartX = getPaddingLeft();
            leftStartY = heightSize - getPaddingBottom()-leftTxtPaint.descent()-2;
            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==CENTER){
            leftStartX = widthSize/2-(measuredWidth)/2;
            leftStartY = heightSize/2+3*leftTxtPaint.getTextSize()/8-2;
            rightStartX = leftStartX+measureLeftText+space4LAndR;
            rightStartY = leftStartY;
        }else if(gravity==RIGHT){
            leftStartX = widthSize -getPaddingRight() - measureLeftText - measureRightText - space4LAndR;
            leftStartY = heightSize-getPaddingBottom() - leftTxtPaint.descent()-2;
            rightStartX = leftStartX + measureLeftText + space4LAndR;
            rightStartY = leftStartY;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getChangeSize();
        canvas.drawText(leftTxt,leftStartX,leftStartY,leftTxtPaint);
        canvas.drawText(rightTxt,rightStartX,rightStartY,rightTxtPaint);
        invalidate();
    }
    public void commit(){
        initPaint();

    }
}