package com.example.dianshangxiangmu.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dianshangxiangmu.R;

public abstract class BaseFragment<V extends IBaseView,P extends IPresenter> extends Fragment implements IBaseView {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayout(),null);
        initView(view);
        presenter=createPresenter();
        presenter.attchView(this);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract P createPresenter();

    protected abstract void initView(View view);

    protected abstract int getLayout();

    @Override
    public void showToast(String str) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter!=null){
            presenter.attchView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
