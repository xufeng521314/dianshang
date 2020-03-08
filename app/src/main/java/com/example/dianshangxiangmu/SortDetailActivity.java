package com.example.dianshangxiangmu;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dianshangxiangmu.adapter.DetilAdapter;
import com.example.dianshangxiangmu.base.BaseActivity;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.DetilListBean;
import com.example.dianshangxiangmu.bean.DetilTabBean;
import com.example.dianshangxiangmu.contract.SortDetilContract;
import com.example.dianshangxiangmu.presenter.DetilPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SortDetailActivity extends BaseActivity<SortDetilContract.View, SortDetilContract.Presenter> implements SortDetilContract.View ,TabLayout.BaseOnTabSelectedListener , BaseAdapter.OnClickItem {

    private ImageView mBackImg;
    private TabLayout mTabLayout;
    private TextView mTitleTxt;
    private TextView mDescTxt;
    private LinearLayout mTxtLayout;
    private RecyclerView mRecyclerview;
    private GridLayoutManager gridLayoutManager;
    private List<DetilListBean.DataBeanX.GoodsListBean> listBeans;
    private DetilAdapter detilAdapter;

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",0);
        //获取tab相关的数据
        presenter.getDetilTab(id);
    }

    @Override
    protected SortDetilContract.Presenter initPresenter() {
        return new DetilPresenter();
    }

    @Override
    protected void initView() {

        mBackImg = (ImageView) findViewById(R.id.img_back);
        mTabLayout = (TabLayout) findViewById(R.id.sortTabLayout);
        mTitleTxt = (TextView) findViewById(R.id.txt_title);
        mDescTxt = (TextView) findViewById(R.id.txt_desc);
        mTxtLayout = (LinearLayout) findViewById(R.id.txtLayout);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerview.setLayoutManager(gridLayoutManager);
        listBeans = new ArrayList<>();
        detilAdapter = new DetilAdapter(listBeans);
        mRecyclerview.setAdapter(detilAdapter);
        detilAdapter.setOnItemClick(this);
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sort_detail;
    }

    @Override
    public void getDetilTabReturn(DetilTabBean result) {
        int postion = -1;
        for(int i=0; i<result.getData().getBrotherCategory().size(); i++){
            DetilTabBean.DataBean.BrotherCategoryBean item = result.getData().getBrotherCategory().get(i);
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(item.getName());
            tab.setTag(item.getId());
            mTabLayout.addTab(tab);
            if(result.getData().getCurrentCategory().getId() == item.getId()){
                postion = i;
            }
        }
        mTitleTxt.setText(result.getData().getCurrentCategory().getName());
        mDescTxt.setText(result.getData().getCurrentCategory().getFront_desc());
        //设置选中的tab
        if(postion >= 0){
            mTabLayout.getTabAt(postion).select();
            //获取tab对应的列表数据
            presenter.getDetilList(result.getData().getCurrentCategory().getId(),1,1000);
        }
    }

    @Override
    public void getDetilReturn(DetilListBean result) {
        listBeans.clear();
        listBeans.addAll(result.getData().getGoodsList());
        detilAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int id = (int) tab.getTag();
        presenter.getDetilList(id,1,1000);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void itenClick(BaseAdapter.BaseViewHolder v, int pos) {
        int id = listBeans.get(pos).getId();
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
