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
        int position = randomNum % Constant.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constant.TAB_COLORS[position];
    }

    public static Drawable getColorDrawable(int radius){
        GradientDrawable drawable  = new GradientDrawable();
        drawable.setColor(randomTagColor());
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
