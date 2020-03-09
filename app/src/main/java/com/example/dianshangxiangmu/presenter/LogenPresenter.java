package com.example.dianshangxiangmu.presenter;

import com.example.dianshangxiangmu.base.BasePresenter;
import com.example.dianshangxiangmu.bean.LogenBean;
import com.example.dianshangxiangmu.bean.VerifyBean;
import com.example.dianshangxiangmu.component.CommonSubscriber;
import com.example.dianshangxiangmu.contract.LogenContract;
import com.example.dianshangxiangmu.model.HttpManager;
import com.example.dianshangxiangmu.utils.RxUtils;

public class LogenPresenter extends BasePresenter<LogenContract.View> implements LogenContract.Presenter {
    @Override
    public void getVerify() {
        addSubscribe(HttpManager.getMyApi().getVerify()
                .compose(RxUtils.<VerifyBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<VerifyBean>(mView){

                    @Override
                    public void onNext(VerifyBean verifyBean) {
                        mView.getVerifyReturn(verifyBean);
                    }
                }));
    }

    @Override
    public void getLogen(String nickname, String password) {
        addSubscribe(HttpManager.getMyApi().logen(nickname,password)
                .compose(RxUtils.<LogenBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<LogenBean>(mView){

                    @Override
                    public void onNext(LogenBean logenBean) {
                        mView.getLogenReturn(logenBean);
                    }
                }));
    }


}
