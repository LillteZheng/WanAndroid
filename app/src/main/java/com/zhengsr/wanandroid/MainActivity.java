package com.zhengsr.wanandroid;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.zhengsr.wanandroid.moudle.activity.DelegateActivity;
import com.zhengsr.wanandroid.moudle.fragment.MainFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends DelegateActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        if (findFragment(MainFragment.class) == null){
            loadRootFragment(R.id.content,MainFragment.newInstance());
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    private long mTime = 0;
    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            long curTime = System.currentTimeMillis();
            if (curTime - mTime > 2000){
                Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
                mTime = curTime;
                return;
            }
            ActivityCompat.finishAfterTransition(this);
        }
    }

}
