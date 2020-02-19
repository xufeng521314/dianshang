package com.example.dianshangxiangmu.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.adapter.CcAdapter;
import com.example.dianshangxiangmu.adapter.HomeAdapter;
import com.example.dianshangxiangmu.adapter.JjAdapter;
import com.example.dianshangxiangmu.adapter.JxAdapter;
import com.example.dianshangxiangmu.adapter.RqAdapter;
import com.example.dianshangxiangmu.adapter.XpAdapter;
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
    private RecyclerView mRecHome;
    private HomeAdapter homeAdapter;
    List<IndexBean.DataBean.BrandListBean> brandList;
    private TabLayout mTabLayout;
    private RecyclerView mXpRec;
    private List<IndexBean.DataBean.NewGoodsListBean> newGoodsListBeanList;
    private XpAdapter xpAdapter;
    private RecyclerView mZtRec;
    private List<IndexBean.DataBean.HotGoodsListBean> hotGoodsListBeans;
    private RqAdapter rqAdapter;
    private RecyclerView mJxRec;
    private List<IndexBean.DataBean.TopicListBean> topicListBeans;
    private JxAdapter jxAdapter;
    private RecyclerView mJjRec;
    private List<IndexBean.DataBean.CategoryListBean.GoodsListBean> goodsListBeans;
    private JjAdapter jjAdapter;
    private RecyclerView mCcRec;
    private CcAdapter ccAdapter;

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
        mRecHome = (RecyclerView) view.findViewById(R.id.home_Rec);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecHome.setLayoutManager(gridLayoutManager);
        brandList = new ArrayList<>();
        homeAdapter = new HomeAdapter(brandList);
        homeAdapter.refresh(brandList);
        mRecHome.setAdapter(homeAdapter);


        mXpRec = (RecyclerView) view.findViewById(R.id.xpRec);
        GridLayoutManager gridManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mXpRec.setLayoutManager(gridManager);
        newGoodsListBeanList = new ArrayList<>();
        xpAdapter = new XpAdapter(newGoodsListBeanList);
        xpAdapter.refresh(newGoodsListBeanList);
        mXpRec.setAdapter(xpAdapter);


        mZtRec = (RecyclerView) view.findViewById(R.id.rqRec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mZtRec.setLayoutManager(linearLayoutManager);
        mZtRec.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        hotGoodsListBeans = new ArrayList<>();
        rqAdapter = new RqAdapter(hotGoodsListBeans);
        rqAdapter.refresh(hotGoodsListBeans);
        mZtRec.setAdapter(rqAdapter);


        mJxRec = (RecyclerView) view.findViewById(R.id.jxRec);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mJxRec.setLayoutManager(manager);
        topicListBeans = new ArrayList<>();
        jxAdapter = new JxAdapter(topicListBeans);
        jxAdapter.refresh(topicListBeans);
        mJxRec.setAdapter(jxAdapter);


        mJjRec = (RecyclerView) view.findViewById(R.id.jjRec);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mJjRec.setLayoutManager(layoutManager);
        goodsListBeans = new ArrayList<>();
        jjAdapter = new JjAdapter(goodsListBeans);
        jjAdapter.refresh(goodsListBeans);
        mJjRec.setAdapter(jjAdapter);


        mCcRec = (RecyclerView) view.findViewById(R.id.ccRec);
        GridLayoutManager ccManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCcRec.setLayoutManager(ccManager);
        List<IndexBean.DataBean.CategoryListBean.GoodsListBean> listBeans=new ArrayList<>();
        ccAdapter = new CcAdapter(listBeans);
        ccAdapter.refresh(listBeans);
        mCcRec.setAdapter(ccAdapter);
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
        xpAdapter.addData(bean.getData().getNewGoodsList());
        rqAdapter.addData(bean.getData().getHotGoodsList());
        jxAdapter.addData(bean.getData().getTopicList());
        jjAdapter.addData(bean.getData().getCategoryList().get(0).getGoodsList());
        ccAdapter.addData(bean.getData().getCategoryList().get(1).getGoodsList());
    }
}
