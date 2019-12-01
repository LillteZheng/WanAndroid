package com.zhengsr.wanandroid.window;

import android.content.Context;
import android.service.quicksettings.Tile;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.utils.DpUtils;
import com.zhengsr.wanandroid.utils.Lgg;

/**
 * @auther by zhengshaorui on 2019/12/1
 * describe:
 */
public class AddShareDialog {

    private final CusDialog mDialog;

    public AddShareDialog(Context context) {
        mDialog = new CusDialog.Builder()
                .setContext(context)
                .setLayoutId(R.layout.dialog_addshare)
                .setWidth(DpUtils.getIntDpSize(context,300))
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutSideDimiss(true)

                .setAnimation(R.style.fadeAnim)
                .builder();
        EditText titleEd = mDialog.getViewbyId(R.id.dialog_share_title);
        EditText linkEd = mDialog.getViewbyId(R.id.dialog_share_link);

        mDialog.setOnClickListener(R.id.dialog_share_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (mListener != null) {
                    String title = titleEd.getText().toString().trim();
                    String link =  linkEd.getText().toString().trim();
                    mListener.getShare(title,link);
                }
            }
        });
    }
    public ShareListener mListener;
    public void setListener(ShareListener listener){
        mListener = listener;
    }
    public interface ShareListener{
        void getShare(String title,String link);
    }
}
