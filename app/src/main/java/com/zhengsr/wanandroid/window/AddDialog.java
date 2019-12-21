package com.zhengsr.wanandroid.window;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.utils.DpUtils;

/**
 * @auther by zhengshaorui on 2019/12/1
 * describe:
 */
public class AddDialog {

    private final CusDialog mDialog;

    public AddDialog(Context context) {
        mDialog = new CusDialog.Builder()
                .setContext(context)
                .setLayoutId(R.layout.dialog_addshare)
                .setWidth(DpUtils.getIntDpSize(context,300))
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutSideDimiss(true)

                .setAnimation(R.style.fadeAnim)
                .builder();
        EditText titleEd = mDialog.getViewbyId(R.id.dialog_share_title);
        EditText authorEd = mDialog.getViewbyId(R.id.dialog_share_author);
        EditText linkEd = mDialog.getViewbyId(R.id.dialog_share_link);

        mDialog.setOnClickListener(R.id.dialog_share_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (mListener != null) {
                    String title = titleEd.getText().toString().trim();
                    String author = authorEd.getText().toString().trim();
                    String link =  linkEd.getText().toString().trim();

                    if (authorEd.getVisibility() == View.VISIBLE) {
                        mListener.onMsg(title, author, link);
                    }else {
                        mListener.onMsg(title,link);
                    }
                }
            }
        });
    }
    public AddListener mListener;
    public AddDialog listener(AddListener listener){
        mListener = listener;
        return this;
    }
    
    public AddDialog title(String title){
        mDialog.setTextView(R.id.dialog_share_top,title);
        return this;
    }

    public AddDialog showAuthor(){
        mDialog.setVisiable(R.id.dialog_share_author,View.VISIBLE);
        return this;
    }

    public AddDialog firstMsg(String firstMsg){
        mDialog.setTextView(R.id.dialog_share_title,firstMsg);
        return this;
    }
    public AddDialog secondMsg(String secondMsg){
        mDialog.setTextView(R.id.dialog_share_link,secondMsg);
        return this;
    }
    public AddDialog btnMsg(String btnMsg){
        mDialog.setTextView(R.id.dialog_share_share,btnMsg);
        return this;
    }

    public static class AddListenerAdapter implements AddListener{

        @Override
        public void onMsg(String title,String link) {

        }

        @Override
        public void onMsg(String title, String author, String link) {

        }
    }

    public interface AddListener {
        void onMsg(String title,String link);
        void onMsg(String title,String author, String link);
    }
}
