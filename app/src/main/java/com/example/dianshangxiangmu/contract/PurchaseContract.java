package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.RelatedBean;

public interface PurchaseContract {

    interface View extends IBaseView{
        void getRelatedDataReturn(RelatedBean result);
    }

    interface Presenter extends IPresenter<View>{
        void getRelatedData(int id);
    }
}
