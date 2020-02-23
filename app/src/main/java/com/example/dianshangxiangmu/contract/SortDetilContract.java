package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.DetilListBean;
import com.example.dianshangxiangmu.bean.DetilTabBean;

public interface SortDetilContract {

    interface View extends IBaseView{
        //加载分类列表的tab数据返回
        void getDetilTabReturn(DetilTabBean result);
        //获取分类类别的tab商品数据返回
        void getDetilReturn(DetilListBean result);
    }

    interface Presenter extends IPresenter<View>{
        //加载分类列表的tab数据
        void getDetilTab(int id);
        //获取分类列表tab所对应的商品数据
        void getDetilList(int categoryId,int page,int size);
    }

}
