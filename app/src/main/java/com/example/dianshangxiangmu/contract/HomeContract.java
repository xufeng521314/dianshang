package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.IndexBean;

public interface HomeContract {
    interface View extends IBaseView{
        void homeData(IndexBean bean);
    }
    interface Presenter extends IPresenter<View>{
        void getHomeData();
    }
}
