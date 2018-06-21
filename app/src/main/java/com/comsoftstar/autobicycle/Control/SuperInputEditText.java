package com.comsoftstar.autobicycle.Control;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.comsoftstar.autobicycle.R;

/**
 * Created by Administrator on 2017/10/16.
 */

public class SuperInputEditText extends AppCompatEditText {
    private int startClick,startunClick,endDelete;
    private Drawable startClickIcon,startunClickIcon,endDeleteIcon;
    int startwidth,starthight,deletewidth,deletehight;
    boolean isclick;
    private GestureDetector gestureDetector;
    public SuperInputEditText(Context context) {
        super(context);
    }

    public SuperInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector=new GestureDetector(context,new EditTextListen());
        init(context,attrs);
    }

    public SuperInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.SuperInputEditText);
        startClick=typedArray.getResourceId(R.styleable.SuperInputEditText_startclickicon, R.drawable.ic_person_white_24dp);
        startClickIcon=getResources().getDrawable(startClick);
        startunClick=typedArray.getResourceId(R.styleable.SuperInputEditText_startunclickicon, R.drawable.ic_person_gray_24dp);
        startunClickIcon=getResources().getDrawable(startunClick);
        endDelete=typedArray.getResourceId(R.styleable.SuperInputEditText_enddeleteicon, R.drawable.ic_clear_black_24dp);
        endDeleteIcon=getResources().getDrawable(endDelete);
        startwidth=typedArray.getInteger(R.styleable.SuperInputEditText_startwidth,80);
        starthight=typedArray.getInteger(R.styleable.SuperInputEditText_starthight,80);
        deletewidth=typedArray.getInteger(R.styleable.SuperInputEditText_endwidth,60);
        deletehight=typedArray.getInteger(R.styleable.SuperInputEditText_endhight,60);

        startClickIcon.setBounds(0,0,startwidth,starthight);
        startunClickIcon.setBounds(0,0,startwidth,starthight);
        endDeleteIcon.setBounds(0,0,deletewidth,deletehight);
        setCompoundDrawables(startunClickIcon,null,null,null);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setEndDeletevisable(isclick&&text.length()>0,isclick);

    }
    private void setEndDeletevisable(boolean b,boolean b2){

            setCompoundDrawables(b2?startClickIcon:startunClickIcon,null,b?endDeleteIcon:null,null);
            setTextColor(b2? Color.parseColor("#ffffffff"):Color.parseColor("#ff888888"));
        invalidate();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        isclick=focused;
        setEndDeletevisable(focused&&length()>0,focused);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.dispatchTouchEvent(event);

    }

    final class EditTextListen implements GestureDetector.OnGestureListener{
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Drawable drawable=endDeleteIcon;
            if (drawable!=null&& motionEvent.getX()<getWidth()&&motionEvent.getX()>getWidth()-drawable.getBounds().width())
            setText("");
            return false;
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }
    }
}
