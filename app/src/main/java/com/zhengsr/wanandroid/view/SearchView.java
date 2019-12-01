package com.zhengsr.wanandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.zhengsr.ariesuilib.InputView;
import com.zhengsr.wanandroid.R;

/**
 * @auther by zhengshaorui on 2019/11/30
 * describe:
 */
public class SearchView extends InputView {
    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ImageView[] leftImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.search);
        imageView.setColorFilter(getResources().getColor(R.color.wechat));
        return new ImageView[]{imageView};
    }

    @Override
    public void config() {
        super.config();
        mEditText.setHint("请输入关键字");

    }


    @Override
    public int[] getLeftImgSize() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());
        return new int[]{width, height};
    }

    @Override
    public ImageView[] rightImageView() {
        return new ImageView[0];
    }

    public String getMsg() {
        return mEditText.getText().toString().trim();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mListener != null) {
            mListener.isEmpty(s.length() <= 0);

        }
    }

    private TextChangeListener mListener;

    public void setListener(TextChangeListener listener) {
        mListener = listener;
    }

    public interface TextChangeListener {
        void isEmpty(boolean isEmpty);
    }
}
