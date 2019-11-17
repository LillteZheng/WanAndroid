package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.zhengsr.ariesuilib.InputView;
import com.zhengsr.wanandroid.R;

/**
 * @auther by zhengshaorui on 2019/11/16
 * describe:
 */
public class UserView extends InputView {
    private static final String TAG = "UserName";

    public UserView(Context context) {
        this(context,null);
    }

    public UserView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ImageView[] leftImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.user);
        return new ImageView[]{imageView};
    }

    @Override
    public ImageView[] rightImageView() {
        ImageView imageview = new ImageView(getContext());
        imageview.setClickable(true);
        imageview.setVisibility(INVISIBLE);
        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageview.setImageResource(R.mipmap.clear);
        imageview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
        return new ImageView[]{imageview};
    }

    @Override
    public void config() {
        super.config();
        mEditText.setHint("请输入用户名");

    }

    @Override
    public int[] getRightImgSize() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());
        return new int[]{width,height};
    }

    @Override
    public int[] getLeftImgSize() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,22,getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,22,getResources().getDisplayMetrics());
        return new int[]{width,height};
    }



}
