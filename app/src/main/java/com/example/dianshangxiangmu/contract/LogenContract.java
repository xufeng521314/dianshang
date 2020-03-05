package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.VerifyBean;

public interface LogenContract {

    interface View extends IBaseView{
        void getVerifyReturn(VerifyBean result);
    }

    interface Presenter extends IPresenter<View> {
        void getVerify();
    }
}
