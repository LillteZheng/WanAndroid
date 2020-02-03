package com.zhengsr.wanandroid.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.zhengsr.wanandroid.Constant;

import java.util.Random;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class CommonUtils {
    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int[] colors = SpfUtils.get(Constant.KEY_IS_NIGHT,false) ? Constant.TAB_COLORS_NIGHT : Constant.TAB_COLORS;
        int position = randomNum % colors.length;
        if (position < 0) {
            position = -position;
        }
        return colors[position];
    }

    public static Drawable getColorDrawable(int radius){
        GradientDrawable drawable  = new GradientDrawable();
        drawable.setColor(randomTagColor());
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
