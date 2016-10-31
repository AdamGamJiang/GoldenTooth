package com.gan.goldentooth.goldentooth.presenter;

import com.gan.goldentooth.goldentooth.base.BasePresenter;
import com.gan.goldentooth.goldentooth.base.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Gam on 2016/10/28.
 * 基于RxJava的presenter的封装，用来控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    protected CompositeSubscription mCompositeSubscription;

    protected void addSubscrebe(Subscription subscription){
        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscrebe(){
        if (mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
