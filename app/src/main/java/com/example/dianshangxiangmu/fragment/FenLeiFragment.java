package com.example.dianshangxiangmu.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseFragment;
import com.example.dianshangxiangmu.bean.CatalogTabBean;
import com.example.dianshangxiangmu.contract.FenLeiContract;
import com.example.dianshangxiangmu.presenter.FenLeiPresenter;

import q.rorbin.verticaltablayout.VerticalTabLayout;

public class FenLeiFragment extends BaseFragment<FenLeiContract.View, FenLeiContract.Presenter> implements FenLeiContract.View {
    private VerticalTabLayout mTab;
    private ImageView mTitleImg;
    private View mVw;
    private RecyclerView mFenleiRec;
    private TextView mFenleiName;

    @Override
    protected void initData() {
        presenter.getFenLeiData();
    }

    @Override
    protected FenLeiContract.Presenter createPresenter() {
        return new FenLeiPresenter();
    }

    @Override
    protected void initView(View view) {
        mTab = (VerticalTabLayout) view.findViewById(R.id.tab);
        mTitleImg = (ImageView) view.findViewById(R.id.titleImg);
        mFenleiRec = (RecyclerView) view.findViewById(R.id.fenleiRec);
        mFenleiName = (TextView) view.findViewById(R.id.fenleiName);
    }

    @Override
    protected int getLayout() {
        return R.layout.fenlei_fragment;
    }

    @Override
    public void getFenLei(CatalogTabBean catalogTabBean) {

    }
}
