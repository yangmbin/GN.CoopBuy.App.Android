package com.coopbuy.mall.ui.module.center.view;

import com.coopbuy.mall.api.login.OrderDetailResponse;
import com.coopbuy.mall.base.BaseView;

public interface OrderDetail_IView extends BaseView {
    void setOrderDetailData(OrderDetailResponse orderDetailResponse);
}