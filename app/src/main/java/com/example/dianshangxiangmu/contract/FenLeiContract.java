package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.CatalogListBean;
import com.example.dianshangxiangmu.bean.CatalogTabBean;

public interface FenLeiContract {
    interface View extends IBaseView{
        void getsortData(CatalogTabBean catalogTabBean);
        void getFenLei(CatalogListBean catalogListBean);
    }

    interface Presenter extends IPresenter<View>{
        void getsort();
        void getFenLeiData(int id);
    }
}
