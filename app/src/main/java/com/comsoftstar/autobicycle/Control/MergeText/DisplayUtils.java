package com.comsoftstar.autobicycle.Control.MergeText;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/12/14.
 */

public class DisplayUtils {
    /**
     * dp转成px
     * @param dip 要转换的dp值
     * @param context   当前上下文
     * @return  px值
     */
    public int dip2px(float dip,Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转换成px
     * @param sp 要转换的sp值
     * @param context 当前上下文
     * @return  px值
     */
    public int sp2px(float sp,Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
