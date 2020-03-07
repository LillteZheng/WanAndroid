package com.zhengsr.wanandroid;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.zhengsr.wanandroid.moudle.activity.BaseDelegateActivity;
import com.zhengsr.wanandroid.moudle.fragment.MainFragment;
import com.zhengsr.wanandroid.net.HttpCreate;
import com.zhengsr.wanandroid.utils.Lgg;
import com.zhengsr.wanandroid.utils.RxUtils;

import io.reactivex.observers.ResourceObserver;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseDelegateActivity {
    private static final String TAG = "MainActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        if (findFragment(MainFragment.class) == null){
            loadRootFragment(R.id.content,MainFragment.newInstance());

            HttpCreate.getServer().getApk()
                    .compose(RxUtils.rxScheduers())
                    .subscribeWith(new ResourceObserver<String>() {
                        @Override
                        public void onNext(String s) {
                            Log.d(TAG, "zsr onNext: "+s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "zsr onError: "+e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultVerticalAnimator();
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
