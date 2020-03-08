package com.example.dianshangxiangmu.presenter;

import com.example.dianshangxiangmu.base.BasePresenter;
import com.example.dianshangxiangmu.bean.CartBean;
import com.example.dianshangxiangmu.bean.CartGoodsCheckBean;
import com.example.dianshangxiangmu.bean.CartGoodsDeleteBean;
import com.example.dianshangxiangmu.bean.CartGoodsUpdateBean;
import com.example.dianshangxiangmu.component.CommonSubscriber;
import com.example.dianshangxiangmu.contract.ShoppingContract;
import com.example.dianshangxiangmu.model.HttpManager;
import com.example.dianshangxiangmu.utils.RxUtils;

import io.reactivex.functions.Function;

public class ShoppingPresenter extends BasePresenter<ShoppingContract.View> implements ShoppingContract.Presenter {

    @Override
    public void getCartIndex() {
        addSubscribe(HttpManager.getMyApi().getCartIndex()
                .compose(RxUtils.<CartBean>rxScheduler())
                .map(new Function<CartBean, CartBean>() {
                    @Override
                    public CartBean apply(CartBean cartBean) throws Exception {
                        for(CartBean.DataBean.CartListBean item:cartBean.getData().getCartList()){
                            item.isSelect = item.getChecked() == 0 ? true : false;
                        }
                        return cartBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean cartBean) {
                        mView.getCartIndexReturn(cartBean);
                    }
                }));
    }

    @Override
    public void setCartGoodsChecked(String pids, int isChecked) {
        addSubscribe(HttpManager.getMyApi().setCartGoodsCheck(pids,isChecked)
                .compose(RxUtils.<CartGoodsCheckBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsCheckBean>(mView) {
                    @Override
                    public void onNext(CartGoodsCheckBean cartBean) {
                        mView.setCartGoodsCheckedReturn(cartBean);
                    }
                }));
    }

    @Override
    public void updateCartGoods(String pids, String goodsId, int number, int id) {
        addSubscribe(HttpManager.getMyApi().updateCartGoods(pids,goodsId,number,id)
                .compose(RxUtils.<CartGoodsUpdateBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsUpdateBean>(mView) {
                    @Override
                    public void onNext(CartGoodsUpdateBean updateBean) {
                        mView.updateCartGoodsReturn(updateBean);
                    }
                }));
    }

    @Override
    public void deleteCartGoods(String pids) {
        addSubscribe(HttpManager.getMyApi().deleteCartGoods(pids)
                .compose(RxUtils.<CartGoodsDeleteBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartGoodsDeleteBean>(mView) {
                    @Override
                    public void onNext(CartGoodsDeleteBean deleteBean) {
                        mView.deleteCartGoodsReturn(deleteBean);
                    }
                }));
    }
}
