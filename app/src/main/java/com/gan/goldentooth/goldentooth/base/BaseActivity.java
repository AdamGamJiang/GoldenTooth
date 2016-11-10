package com.gan.goldentooth.goldentooth.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.boredream.bdcodehelper.base.BoreBaseActivity;
import com.gan.goldentooth.goldentooth.constants.CommentConstant;

import java.util.concurrent.TimeUnit;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Gam on 2016/10/28.
 * 所有activity基类
 */
public class BaseActivity extends BoreBaseActivity implements SwipeBackActivityBase {

    private BaseApplication application;
    private SharedPreferences sp;
    protected boolean isActive;  //当前界面是否处于活动状态
    private boolean canDoubleBackExit;  //双击返回键退出
    private boolean theFirstPressExit;  //第一次按下返回键
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果是退出，就不继续加载了
        boolean isExit = getIntent().getBooleanExtra("exit", false);
        if(isExit){
            finish();
            return;
        }

        application = (BaseApplication) getApplication();
        sp = getSharedPreferences(CommentConstant.SP_NAME, MODE_PRIVATE);

        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    //设置是否可以双击返回键退出
    public void setCanDoubleBackExit(boolean canDoubleBackExit){
        this.canDoubleBackExit = canDoubleBackExit;
    }

    @Override
    public void onBackPressed() {
        if(!canDoubleBackExit){//不能双击退出
            super.onBackPressed();
            return;
        }

        //双击关闭程序
        //如果两秒之内再次按下返回键，则退出应用
        if(theFirstPressExit){
            System.exit(0);
            return;
        }

        theFirstPressExit = true;
        showToast("再按一次退出应用");
        Observable.just(null)
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        //两秒后没按下，把theFirstPressExit置未false
                        theFirstPressExit = false;
                    }
                });
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    //设置可以滑动返回
    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
