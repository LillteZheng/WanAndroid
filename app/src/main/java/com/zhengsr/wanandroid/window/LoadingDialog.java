package com.zhengsr.wanandroid.window;

import android.content.Context;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.utils.DpUtils;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class LoadingDialog {

    private final CusDialog mDialog;

    public LoadingDialog(Context context,String msg) {
        mDialog = new CusDialog.Builder()
                .setContext(context)
                .setLayoutId(R.layout.dialog_loaing)
                .setWidth(DpUtils.getIntDpSize(context,200))
                .setHeight(DpUtils.getIntDpSize(context,120))
                .setOutSideDimiss(false)
                .setAnimation(R.style.fadeAnim)
                .builder();

        mDialog.setTextView(R.id.loading_msg,msg);
    }
    public void dismiss(){
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
    public void updateMsg(String msg){
        mDialog.setTextView(R.id.loading_msg,msg);
    }
}
