package com.gan.goldentooth.goldentooth.base;

/**
 * Created by Gam on 2016/10/28.
 * Presenter的基类
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
