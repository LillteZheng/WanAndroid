package com.zhengsr.wanandroid.moudle.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.LoginBean;
import com.zhengsr.wanandroid.bean.RegisterBean;
import com.zhengsr.wanandroid.moudle.activity.BaseDelegateActivity;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.UserPresent;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.view.PasswordView;
import com.zhengsr.wanandroid.view.UserView;
import com.zhengsr.wanandroid.window.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther by zhengshaorui on 2019/11/13
 * describe:
 */
public class LoginActivity extends BaseDelegateActivity<UserPresent> implements IContractView.ILoginView {
    private static final String TAG = "LoginActivity";
    private static final int ANIM_TIME = 400;

    private UserPresent mPresent;
    private LoadingDialog mDialog;


    @BindView(R.id.login_in)
    View mLoginView;
    @BindView(R.id.login_register)
    View mRegisterView;
    /**
     * register
     */
    private EditText mRegisterUserEt;
    private EditText mRegisterPassEt;
    private EditText mRegisterRePassEt;

    /**
     * login
     */
    private EditText mLoginUserEt;
    private EditText mLoginPassEt;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }


    @Override
    public UserPresent getPresent() {
        mPresent = UserPresent.create(this);
        return mPresent;
    }

    @Override
    public void initView() {
        super.initView();
        mRegisterView.setVisibility(View.VISIBLE);
        initLoginView();
    }

    private void initLoginView() {
        /**
         * login
         */
        UserView userView = mLoginView.findViewById(R.id.user_name);
        PasswordView passView = mLoginView.findViewById(R.id.password);
        mLoginUserEt = userView.getEditText();
        mLoginPassEt = passView.getEditText();
        mLoginUserEt.setText("zhengsr");
        mLoginPassEt.setText("17320220zsr");
        mLoginUserEt.setSelection(mLoginUserEt.getText().length());
        /**
         * register
         */
        UserView userView1 = mRegisterView.findViewById(R.id.register_username);
        PasswordView passwordView = mRegisterView.findViewById(R.id.register_password);
        PasswordView passwordView2 = mRegisterView.findViewById(R.id.register_repassword);
        mRegisterUserEt = userView1.getEditText();
        mRegisterPassEt = passwordView.getEditText();
        mRegisterRePassEt = passwordView2.getEditText();

        mRegisterUserEt.setText("zhengsr_123sd");
        mRegisterPassEt.setText("12345678");
        mRegisterRePassEt.setText("12345678");
        mRegisterUserEt.setSelection(mRegisterUserEt.getText().length());


    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        slideAnim(true,0);
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();
    }

    @OnClick({R.id.login_register_iv,R.id.login_login_iv,R.id.bar_back,R.id.login_in_tv,R.id.register_tv})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.login_register_iv:
                slideAnim(false,ANIM_TIME);
                break;
            case R.id.login_login_iv:
                slideAnim(true,ANIM_TIME);
                break;
            case R.id.bar_back:
                finish();
                break;
            case R.id.login_in_tv:
                mDialog = new LoadingDialog(this,"正在登录...");
                mPresent.login(mLoginUserEt.getText().toString().trim(),mLoginPassEt.getText().toString().trim());
                break;
            case R.id.register_tv:
                mDialog = new LoadingDialog(this,"正在注册...");
                String username = mRegisterUserEt.getText().toString().trim();
                String password = mRegisterPassEt.getText().toString().trim();
                String repassword = mRegisterRePassEt.getText().toString().trim();
                mPresent.register(username,password,repassword);
                break;
                default:break;
        }
    }

    @Override
    protected int setTitleBar() {
        return R.id.common_bar;
    }



    /**
     * 实现左右划页
     * @param left
     * @param time
     */
    private void slideAnim(boolean left,int time){
        if (left){
            mLoginView.setVisibility(View.VISIBLE);
        }else{
            mRegisterView.setVisibility(View.VISIBLE);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator loginAnimTrans;
        ObjectAnimator reAnimTrans;
        ObjectAnimator reAnimAlpha;
        ObjectAnimator loginAnimAlpha;
        if (left){
            loginAnimTrans = ObjectAnimator.ofFloat(mLoginView,"translationX",mLoginView.getWidth(),0);
            loginAnimAlpha = ObjectAnimator.ofFloat(mLoginView,"alpha",0,1);
            reAnimTrans = ObjectAnimator.ofFloat(mRegisterView,"translationX",0,-mRegisterView.getWidth());
            reAnimAlpha = ObjectAnimator.ofFloat(mRegisterView,"alpha",1,0);

        }else{
            reAnimTrans = ObjectAnimator.ofFloat(mRegisterView,"translationX",-mRegisterView.getWidth(),0);
            reAnimAlpha = ObjectAnimator.ofFloat(mRegisterView,"alpha",0,1);
            loginAnimTrans = ObjectAnimator.ofFloat(mLoginView,"translationX",0,mLoginView.getWidth());
            loginAnimAlpha = ObjectAnimator.ofFloat(mLoginView,"alpha",1,0);

        }
        animatorSet.setDuration(time);
        animatorSet.play(loginAnimTrans).with(loginAnimAlpha).with(reAnimTrans).with(reAnimAlpha);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (left){
                    mRegisterView.setVisibility(View.GONE);
                }else{
                    mLoginView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void loginInfo(LoginBean loginBean) {
        Intent intent = new Intent();
        intent.putExtra("bean",loginBean);
       // intent.puts("bean", );
        // 设置返回码和返回携带的数据
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    @Override
    public void registerInfo(RegisterBean bean) {
        Lgg.d("register: "+bean);
        mDialog.updateMsg("注册成功，正在登录..");
        mPresent.login(bean.getUsername(),mRegisterPassEt.getText().toString().trim());
    }

    @Override
    public void loadSuccess() {
        super.loadSuccess();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        super.showErrorMsg(msg);
        if (mDialog != null) {
            mDialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

