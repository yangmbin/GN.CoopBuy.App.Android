package com.coopbuy.mall.ui.mainpage.presenter;


import android.content.Context;

import com.coopbuy.mall.R;
import com.coopbuy.mall.api.reponse.HomeFloorResponse;
import com.coopbuy.mall.base.BasePresenter;
import com.coopbuy.mall.ui.mainpage.model.HomeModel;
import com.coopbuy.mall.ui.mainpage.view.Home_IView;
import com.coopbuy.mall.utils.ToastUtils;
import com.guinong.net.CodeContant;
import com.guinong.net.NetworkException;
import com.guinong.net.callback.IAsyncResultCallback;

import java.util.List;

public class HomePresenter extends BasePresenter<Home_IView, HomeModel> {

    public HomePresenter(Context context, HomeModel model, Home_IView view) {
        super(context, model, view);
    }

    /**
     * 获取首页数据
     */
    public void getHomeData(final boolean isPullToRefresh) {
        if (!isPullToRefresh)
            mView.showFillLoading();
        mView.appendNetCall(mModel.getHomeFloorList(new IAsyncResultCallback<List<HomeFloorResponse>>() {
            @Override
            public void onComplete(List<HomeFloorResponse> homeFloorResponseList, Object userState) {
                mView.setHomeData(homeFloorResponseList);
                if (isPullToRefresh)
                    mView.stopPullToRefreshLoading();
                else
                    mView.stopAll();

                if (homeFloorResponseList.size() == 0)
                    mView.showNoDataLayout();
            }

            @Override
            public void onError(NetworkException error, Object userState) {
                if (isPullToRefresh)
                    mView.stopPullToRefreshLoading();
                else
                    mView.stopAll();

                if (error.getCode() == CodeContant.CODE_NET_UNAVAILABLE) {
                    if (!isPullToRefresh)
                        mView.showNetOffLayout();
                    ToastUtils.toastShort(R.string.no_network);
                } else {
                    if (!isPullToRefresh)
                        mView.showNetErrorLayout();
                    ToastUtils.toastShort(R.string.connect_server_error);
                }
            }
        }, "获取首页数据"));
    }
}
