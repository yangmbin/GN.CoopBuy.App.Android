package com.coopbuy.mall.ui.module.center.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coopbuy.mall.R;
import com.coopbuy.mall.api.reponse.MessageCenterResponse;
import com.coopbuy.mall.base.BaseActivity;
import com.coopbuy.mall.ui.module.center.adapter.IncomeAdapter;
import com.coopbuy.mall.ui.module.center.adapter.MyCustomAdapter;
import com.coopbuy.mall.ui.module.center.port.FootMarkPort;
import com.coopbuy.mall.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MyCustomActivity extends BaseActivity implements FootMarkPort {
    @Bind(R.id.recView)
    RecyclerView recView;
    private MyCustomAdapter adapter;
    private List<MessageCenterResponse> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_custom;
    }

    @Override
    public void initModel() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        setTitle(getString(R.string.title_custom));
        setRightText(getString(R.string.lab_custom_add));
        initRey();
    }

    private void initRey() {
        data = new ArrayList<>();
        setData();
        adapter = new MyCustomAdapter(data, this);
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.setAdapter(adapter);
    }

    private void setData() {
        for (int i = 0; i < 10; i++) {
            MessageCenterResponse d = new MessageCenterResponse();
            d.setDetail("ssssdf" + i);
            if (i == 0)
                d.setName("悟空物流");
            if (i == 1)
                d.setName("八戒物流");
            if (i == 2)
                d.setName("沙僧物流");
            if (i > 2)
                d.setName("other物流" + i);
            d.setTime("2012." + i);
            d.setType(i);
            data.add(d);
        }
    }

    @Override
    public void clickTitleBarRight() {
        super.clickTitleBarRight();
        IntentUtils.gotoActivity(this, AddCustomActivity.class);
    }

    @Override
    public void openDetial(int postion) {

    }
}
