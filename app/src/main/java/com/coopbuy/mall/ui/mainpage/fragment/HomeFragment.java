package com.coopbuy.mall.ui.mainpage.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.coopbuy.mall.R;
import com.coopbuy.mall.api.login.HomePageDataResponse;
import com.coopbuy.mall.base.ViewPagerBaseFragment;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_1;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_2;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_3;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_4;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_5;
import com.coopbuy.mall.ui.mainpage.adapter.HomeLayoutAdapter_8;
import com.coopbuy.mall.ui.mainpage.model.HomeModel;
import com.coopbuy.mall.ui.mainpage.presenter.HomePresenter;
import com.coopbuy.mall.ui.mainpage.view.Home_IView;
import com.coopbuy.mall.utils.ScreenUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * 主页Fragment
 *
 * @author ymb
 *         Create at 2017/7/25 10:23
 */
//@RuntimePermissions
public class HomeFragment extends ViewPagerBaseFragment<HomePresenter, HomeModel> implements Home_IView, OnRefreshListener {

    public static final String LAYOUT_TYPE_1 = "1"; // 首页顶部banner
    public static final String LAYOUT_TYPE_2 = "2"; // 特殊入口
    public static final String LAYOUT_TYPE_3 = "3"; // 新人专享
    public static final String LAYOUT_TYPE_4 = "4"; // 模块类型1
    public static final String LAYOUT_TYPE_5 = "5"; // 模块类型2

    @Bind(R.id.rv_home)
    RecyclerView mRvHome;
    @Bind(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private DelegateAdapter mDelegateAdapter;
    private List<DelegateAdapter.Adapter> mAdapters = new LinkedList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initModel() {
        mModel = new HomeModel();
    }

    @Override
    public void initPresenter() {
        mPresenter = new HomePresenter(mContext, mModel, this);
    }

    @Override
    protected void initView() {
        VirtualLayoutManager manager = new VirtualLayoutManager(mContext);
        mRvHome.setLayoutManager(manager);
        mDelegateAdapter = new DelegateAdapter(manager, false);
        mRvHome.setAdapter(mDelegateAdapter);
        mRefreshLayout.setOnRefreshListener(this);

        // test
        mAdapters.clear();
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvHome.setRecycledViewPool(viewPool);
//        viewPool.setMaxRecycledViews(i, 10);
        List<HomePageDataResponse.FloorsBean> decoration;

        //1
        decoration = new ArrayList<>();
        decoration.add(new HomePageDataResponse.FloorsBean());
        mAdapters.add(new HomeLayoutAdapter_1(mContext, decoration, new SingleLayoutHelper()));

        //2
        GridLayoutHelper helper_2 = new GridLayoutHelper(5);
        helper_2.setPaddingTop(ScreenUtils.dip2px(mContext, 20));
        helper_2.setPaddingBottom(ScreenUtils.dip2px(mContext, 22));
        helper_2.setVGap(ScreenUtils.dip2px(mContext, 20));
        helper_2.setBgColor(ContextCompat.getColor(mContext, R.color.white));
        helper_2.setAutoExpand(false);
        List<HomePageDataResponse.FloorsBean.FloorItemsBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(new HomePageDataResponse.FloorsBean.FloorItemsBean());
        mAdapters.add(new HomeLayoutAdapter_2(mContext, list, helper_2));

        //3
        decoration = new ArrayList<>();
        decoration.add(new HomePageDataResponse.FloorsBean());
        mAdapters.add(new HomeLayoutAdapter_3(mContext, decoration, new SingleLayoutHelper()));

        //4
        GridLayoutHelper helper_4 = new GridLayoutHelper(2);
        helper_4.setAutoExpand(false);
        helper_4.setMarginTop(ScreenUtils.dip2px(mContext, 8));
        helper_4.setGap(ScreenUtils.dip2px(mContext, 1));
        decoration = new ArrayList<>();
        decoration.add(new HomePageDataResponse.FloorsBean());
        decoration.add(new HomePageDataResponse.FloorsBean());
        decoration.add(new HomePageDataResponse.FloorsBean());
        decoration.add(new HomePageDataResponse.FloorsBean());
        mAdapters.add(new HomeLayoutAdapter_4(mContext, decoration, helper_4));

        //5
        SingleLayoutHelper helper_5 = new SingleLayoutHelper();
        helper_5.setMarginTop(ScreenUtils.dip2px(mContext, 6));
        decoration = new ArrayList<>();
        decoration.add(new HomePageDataResponse.FloorsBean());
        mAdapters.add(new HomeLayoutAdapter_5(mContext, decoration, helper_5));

//        LinearLayoutHelper linearListHelper = new LinearLayoutHelper();
//        linearListHelper.setDividerHeight(ScreenUtils.dp2px(mContext, 1));
//        mAdapters.add(new HomeLayoutAdapter_6(mContext, floors.get(i).getFloorItems(), linearListHelper));
//
//        GridLayoutHelper gridListThreeHelper = new GridLayoutHelper(3);
//        gridListThreeHelper.setAutoExpand(false);
//        gridListThreeHelper.setMarginTop(ScreenUtils.dp2px(mContext, 5));
        //  mAdapters.add(new HomeLayoutAdapter_7(mContext, floors.get(i).getFloorItems(), gridListThreeHelper));

        mDelegateAdapter.setAdapters(mAdapters);
    }

    @Override
    protected void onFragmentFirstVisible() {
    }

    @Override
    protected void networkRetry() {
    }

    @Override
    public void setHomeData(HomePageDataResponse homePageDataResponse) {
        if (homePageDataResponse == null)
            return;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    @Override
    public void stopPullToRefreshLoading() {
        if (mRefreshLayout != null)
            mRefreshLayout.finishRefresh();
    }

    /**
     * 悬浮按钮点击返回顶部按钮和监听
     */
    private void goBackTopPosition() {
        if (mAdapters.size() == 4) {
            ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, ScreenUtils.dip2px(mContext, 15), ScreenUtils.dip2px(mContext, 15));
            scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
            List<Object> tmp = new ArrayList<>();
            tmp.add(new Object());
            mAdapters.add(new HomeLayoutAdapter_8(mContext, tmp, scrollFixLayoutHelper, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemCount = 0;
                    for (int i = 0; i < mAdapters.size(); i++) {
                        if (i == 5)
                            break;
                        itemCount += mAdapters.get(i).getItemCount();
                    }
                    mRvHome.scrollToPosition(itemCount);
                    mRvHome.smoothScrollToPosition(0);
                }
            }));
        }
    }


//    /**
//     * 二维码扫描相关动态权限处理
//     */
//    @OnShowRationale({android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void showRationaleForPermission(final PermissionRequest request) {
//        new AlertDialog.Builder(mContext)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("扫描二维码需要相机和SD卡权限")
//                .show();
//    }
//
//    @NeedsPermission({android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void gotoScanQRCodeActivity() {
//        IntentUtils.gotoActivity(mContext, ScanQrCodeActivity.class);
//    }
//
//    @OnPermissionDenied({android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void showPermissionDenied() {
//        ToastUtils.toastShort("您已拒绝打开权限");
//    }
//
//    @OnNeverAskAgain({android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    public void onRecordNeverAskAgain() {
//        new AlertDialog.Builder(mContext)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent();
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        if(Build.VERSION.SDK_INT >= 9){
//                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                            intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
//                        } else if(Build.VERSION.SDK_INT <= 8){
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
//                            intent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
//                        }
//                        startActivity(intent);
//                        dialog.cancel();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("您已经禁止了相关权限，是否现在去开启")
//                .show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        HomeFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    public void requestPermission() {
//        HomeFragmentPermissionsDispatcher.gotoScanQRCodeActivityWithCheck(this);
//    }
}