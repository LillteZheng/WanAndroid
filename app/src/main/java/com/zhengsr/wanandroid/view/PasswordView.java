package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhengsr.ariesuilib.InputView;
import com.zhengsr.wanandroid.R;

/**
 * @auther by zhengshaorui on 2019/11/16
 * describe:
 */
public class PasswordView extends InputView {
    private static final String TAG = "UserName";

    public PasswordView(Context context) {
        this(context,null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ImageView[] leftImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.lock);
        return new ImageView[]{imageView};
    }

    @Override
    public ImageView[] rightImageView() {
        ImageView clearView = new ImageView(getContext());
        clearView.setClickable(true);
        clearView.setVisibility(INVISIBLE);
        clearView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        clearView.setImageResource(R.mipmap.clear);
        clearView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });

        final ImageView eyeView = new ImageView(getContext());
        eyeView.setClickable(true);
        eyeView.setVisibility(INVISIBLE);
        eyeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        eyeView.setImageResource(R.mipmap.eye_close);
        eyeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdHideMode()){
                    changeEdPassMode(true);
                    eyeView.setImageResource(R.mipmap.eye_close);
                }else{
                    changeEdPassMode(false);
                    eyeView.setImageResource(R.mipmap.eye);
                }
            }
        });
        return new ImageView[]{clearView,eyeView};
    }

    @Override
    public void config() {
        super.config();
        changeEdPassMode(true);
        mEditText.setHint("请输入密码");
    }

    @Override
    public int[] getRightImgSize() {
        int width =  getIntDpSize(20);
        int height =  getIntDpSize(20);
        return new int[]{width,height};
    }

    @Override
    public int[] getLeftImgSize() {
        int width =  getIntDpSize(22);
        int height =  getIntDpSize(22);
        return new int[]{width,height};
    }
    public EditText getEditText(){
        return mEditText;
    }

}
