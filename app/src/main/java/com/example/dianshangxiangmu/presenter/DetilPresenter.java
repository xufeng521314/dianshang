package com.example.dianshangxiangmu.presenter;

import com.example.dianshangxiangmu.base.BasePresenter;
import com.example.dianshangxiangmu.bean.DetilListBean;
import com.example.dianshangxiangmu.bean.DetilTabBean;
import com.example.dianshangxiangmu.component.CommonSubscriber;
import com.example.dianshangxiangmu.contract.SortDetilContract;
import com.example.dianshangxiangmu.model.HttpManager;
import com.example.dianshangxiangmu.utils.RxUtils;

public class DetilPresenter extends BasePresenter<SortDetilContract.View> implements SortDetilContract.Presenter {
    @Override
    public void getDetilTab(int id) {
        addSubscribe(HttpManager.getMyApi().getDetilTab(id)
                .compose(RxUtils.<DetilTabBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DetilTabBean>(mView){
                    @Override
                    public void onNext(DetilTabBean detilTabBean) {
                        mView.getDetilTabReturn(detilTabBean);
                    }
                }));
    }

    @Override
    public void getDetilList(int categoryId, int page, int size) {
        addSubscribe(HttpManager.getMyApi().getDetilList(categoryId,page,size)
                .compose(RxUtils.<DetilListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DetilListBean>(mView){
                    @Override
                    public void onNext(DetilListBean detilListBean) {
                        mView.getDetilReturn(detilListBean);
                    }
                }));
    }
}
