package com.example.dianshangxiangmu.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.adapter.HomeAdapter;
import com.example.dianshangxiangmu.base.BaseFragment;
import com.example.dianshangxiangmu.bean.IndexBean;
import com.example.dianshangxiangmu.contract.HomeContract;
import com.example.dianshangxiangmu.presenter.HomePresenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {
    private Banner mBanner;
    private TextView mTitleHome;
    private RecyclerView mRecHome;
    private HomeAdapter homeAdapter;
    List<IndexBean.DataBean.BrandListBean> brandList;
    private TabLayout mTabLayout;

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mTitleHome = (TextView) view.findViewById(R.id.home_Title);
        mRecHome = (RecyclerView) view.findViewById(R.id.home_Rec);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTitleHome.setText("品牌制造商直供");
        mRecHome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        brandList = new ArrayList<>();
        homeAdapter = new HomeAdapter(brandList);
        mRecHome.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    public void homeData(IndexBean bean) {
        List<IndexBean.DataBean.BannerBean> bannerBeanList = bean.getData().getBanner();
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < bannerBeanList.size(); i++) {
            String url = bannerBeanList.get(i).getImage_url();
            list.add(url);
        }
        mBanner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        }).start();

        List<IndexBean.DataBean.ChannelBean> channel = bean.getData().getChannel();
        for (int i = 0; i < channel.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(channel.get(i).getName()));
        }

        homeAdapter.addData(bean.getData().getBrandList());
    }
}