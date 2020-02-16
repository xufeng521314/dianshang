package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.IndexBean;

public interface ZhuanTiContract {

    interface View extends IBaseView{
        void getZhuanTi(IndexBean zhuanti);
    }

    interface Presenter extends IPresenter<View>{
        void getZhuanTiData();
    }

}
