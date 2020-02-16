package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.CatalogTabBean;

public interface FenLeiContract {
    interface View extends IBaseView{
        void getFenLei(CatalogTabBean catalogTabBean);
    }

    interface Presenter extends IPresenter<View>{
        void getFenLeiData();
    }
}
