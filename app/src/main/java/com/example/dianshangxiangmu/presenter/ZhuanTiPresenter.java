package com.example.dianshangxiangmu.presenter;

import com.example.dianshangxiangmu.base.BasePresenter;
import com.example.dianshangxiangmu.bean.IndexBean;
import com.example.dianshangxiangmu.component.CommonSubscriber;
import com.example.dianshangxiangmu.contract.ZhuanTiContract;
import com.example.dianshangxiangmu.model.HttpManager;
import com.example.dianshangxiangmu.utils.RxUtils;

public class ZhuanTiPresenter extends BasePresenter<ZhuanTiContract.View> implements ZhuanTiContract.Presenter {
    @Override
    public void getZhuanTiData() {
        addSubscribe(HttpManager.getMyApi().getIndexData()
                .compose(RxUtils.<IndexBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<IndexBean>(mView){

                    @Override
                    public void onNext(IndexBean result) {
                        mView.getZhuanTi(result);
                    }
                }));
    }
}
