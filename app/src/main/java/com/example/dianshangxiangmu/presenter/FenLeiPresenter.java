package com.example.dianshangxiangmu.presenter;

import com.example.dianshangxiangmu.base.BasePresenter;
import com.example.dianshangxiangmu.bean.CatalogListBean;
import com.example.dianshangxiangmu.bean.CatalogTabBean;
import com.example.dianshangxiangmu.component.CommonSubscriber;
import com.example.dianshangxiangmu.contract.FenLeiContract;
import com.example.dianshangxiangmu.model.HttpManager;
import com.example.dianshangxiangmu.utils.RxUtils;

public class FenLeiPresenter extends BasePresenter<FenLeiContract.View> implements FenLeiContract.Presenter {

    @Override
    public void getsort() {
        addSubscribe(HttpManager.getMyApi().getCatalogTabData()
                .compose(RxUtils.<CatalogTabBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CatalogTabBean>(mView){

                    @Override
                    public void onNext(CatalogTabBean catalogTabBean) {
                        if(catalogTabBean.getErrno() == 0){
                            mView.getsortData(catalogTabBean);
                        }
                    }
                }));
    }

    @Override
    public void getFenLeiData(int id) {
        addSubscribe(HttpManager.getMyApi().getCatalogList(id)
                .compose(RxUtils.<CatalogListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CatalogListBean>(mView){

                    @Override
                    public void onNext(CatalogListBean catalogListBean) {
                        if(catalogListBean.getErrno() == 0){
                            mView.getFenLei(catalogListBean);
                        }
                    }
                }));
    }
}
