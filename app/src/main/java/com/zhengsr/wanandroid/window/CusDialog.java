package com.zhengsr.wanandroid.window;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class CusDialog  {
    private static final String TAG = "CusDialog";
    private final Mydialog mDialog;
    private View mContentView;
    private int mLayoutId;
    private Context mContext;
    public CusDialog(Builder builder){
        mLayoutId = builder.layoutid;
        mContext = builder.context;
        mDialog = new Mydialog(builder.context,builder);
        mDialog.show(); //必须先show，否则 create 不会创建，所以 contentview 会为null,这里id就获取不到了
    }

    public void show(){
        if (mDialog != null){
            mDialog.show();
        }
    }
    public void dismiss(){
        if (mDialog != null){
            mDialog.dismiss();
        }
    }

    public  <T extends View> T getViewbyId(int id){
        
       if (mDialog != null){
           return (T) mDialog.getContentView().findViewById(id);
       }
        return null;


    }

    public boolean isShowing(){
        return mDialog.isShowing();
    }

    public CusDialog setVisiable(int viewid,int visiable){
        getViewbyId(viewid).setVisibility(visiable);
        return this;
    }

    public CusDialog setTextView(int viewid,String msg){
        TextView textView = getViewbyId(viewid);
        if (msg != null) {
            textView.setText(msg);
        }
        return this;
    }



    public CusDialog setImageview(int viewid,String path){
        //TextView textView = getViewbyId(viewid);
        ImageView imageView = getViewbyId(viewid);
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        return this;
    }

    public CusDialog setEnable(int viewid,boolean enable){
        getViewbyId(viewid).setEnabled(enable);
        return this;
    }
    public CusDialog setOnClickListener(int viewid, View.OnClickListener listener){
        View view = getViewbyId(viewid);
        view.setClickable(true);
        view.setOnClickListener(listener);
        return this;
    }
    public CusDialog setOnCancelListener(DialogInterface.OnCancelListener litener){
        if (mDialog != null){
            mDialog.setOnCancelListener(litener);
        }
        return this;
    }


    public static class Builder{
        int layoutid = -1;
        boolean outsideDimiss;
        Context context;
        int width;
        int height;
        int styleanim;
        View view;
        int gravity = -1;
        boolean isSystem = false;

        public Builder setContext(Context context){
            this.context = context;
            return this;
        }

        public Builder setLayoutId(int layoutid){
            this.layoutid = layoutid;
            return this;
        }
        
        public Builder isSystem(boolean issystem){
        	this.isSystem = issystem;
        	return this;
        }

        public Builder setLayoutView(View view){
            this.view = view;
            return this;
        }
        public Builder setWidth(int width){
            this.width = width;
            return this;
        }
        public Builder setHeight(int height){
            this.height = height;
            return this;
        }

        public Builder setAnimation(int styleanim){
            this.styleanim = styleanim;
            return this;
        }
        public Builder setOutSideDimiss(boolean outSideDimiss){
            this.outsideDimiss = outSideDimiss;
            return this;
        }

        public Builder setGravity(int gravity){
            this.gravity = gravity;
            return this;
        }


        public CusDialog builder(){
            CusDialog dialog = new CusDialog(this);
            return dialog;
        }
    }


    class Mydialog extends Dialog {

        Builder builder;
        View contentview;

        public Mydialog(Context context, Builder builder) {
            super(context);
            this.builder = builder;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //配置 dialog 的window 参数
            Window window = getWindow();
            if (builder.gravity != -1){
                window.setGravity(builder.gravity);
            }

            if(builder.isSystem){
            	window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }
            if (builder.styleanim != 0) {
                window.setWindowAnimations(builder.styleanim);
            }
            window.setBackgroundDrawableResource(android.R.color.transparent);
            if(builder.layoutid != -1){
                this.contentview = LayoutInflater.from(builder.context).
                        inflate(this.builder.layoutid,null);
            }
            if (builder.view != null){
                this.contentview = builder.view;
            }

            setContentView( this.contentview);

            //设置位置大小的参数
            WindowManager.LayoutParams lp = window.getAttributes();
            if (builder.width != 0 && builder.height != 0){
                lp.width = builder.width;
                lp.height = builder.height;
            }

            getWindow().setAttributes(lp);
            setCanceledOnTouchOutside(this.builder.outsideDimiss);// 点击Dialog外部消失
        }


        public View getContentView(){
            return  this.contentview;
        }


    }


}