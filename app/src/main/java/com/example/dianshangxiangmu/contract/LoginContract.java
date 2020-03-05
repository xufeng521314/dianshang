package com.example.dianshangxiangmu.contract;

import com.example.dianshangxiangmu.base.IBaseView;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.bean.UserBean;

public interface LoginContract {

    interface View extends IBaseView{
        void loginReturn(UserBean result);
    }

    interface Presenter extends IPresenter<View>{
        void login(String nickname,String password);
    }
}
