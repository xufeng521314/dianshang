package com.example.dianshangxiangmu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<V extends IBaseView,P extends IPresenter> extends AppCompatActivity implements IBaseView{

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        presenter = initPresenter();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter!=null){
            presenter.attchView(this);
        }
    }

    @Override
    public void showToast(String str) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }

    protected abstract void initData();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int getLayout();
}
