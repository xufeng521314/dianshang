package com.example.dianshangxiangmu.base;

public interface IPresenter<V extends IBaseView> {
    //p层关联V层
    void attchView(V view);
    //p层释放v层
    void detachView();
}
