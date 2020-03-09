package com.example.dianshangxiangmu.model;




import com.example.dianshangxiangmu.bean.CartBean;
import com.example.dianshangxiangmu.bean.CartGoodsCheckBean;
import com.example.dianshangxiangmu.bean.CartGoodsDeleteBean;
import com.example.dianshangxiangmu.bean.CartGoodsUpdateBean;
import com.example.dianshangxiangmu.bean.CatalogListBean;
import com.example.dianshangxiangmu.bean.CatalogTabBean;
import com.example.dianshangxiangmu.bean.DetilListBean;
import com.example.dianshangxiangmu.bean.DetilTabBean;
import com.example.dianshangxiangmu.bean.IndexBean;
import com.example.dianshangxiangmu.bean.LogenBean;
import com.example.dianshangxiangmu.bean.RelatedBean;
import com.example.dianshangxiangmu.bean.UserBean;
import com.example.dianshangxiangmu.bean.VerifyBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApi {


    //主页数据接口
    @GET("index")
    Flowable<IndexBean> getIndexData();

    //获取分类导航数据接口
    @GET("catalog/index")
    Flowable<CatalogTabBean> getCatalogTabData();

    //获取列表选中的数据
    @GET("catalog/current")
    Flowable<CatalogListBean> getCatalogList(@Query("id") int id);

    //获取分类详情页Tab数据
    @GET("goods/category")
    Flowable<DetilTabBean> getDetilTab(@Query("id") int id);

    //获取分类详情页列表数据
    @GET("goods/list")
    Flowable<DetilListBean> getDetilList(@Query("categoryId") int id,@Query("page") int page,@Query("size") int size);

    //商品购买页面的数据接口
    @GET("goods/detail")
    Flowable<RelatedBean> getRelatedData(@Query("id") int id);

    //验证码
    @GET("auth/verify")
    Flowable<VerifyBean> getVerify();

    //注册
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<LogenBean> logen (@Field("nickname") String nickname, @Field("password") String password);

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> login(@Field("nickname") String nickname, @Field("password") String password);

    //获取购物车的数据
    @GET("cart/index")
    Flowable<CartBean> getCartIndex();

    //购物车商品数据的选中或取消
    @POST("cart/checked")
    @FormUrlEncoded
    Flowable<CartGoodsCheckBean> setCartGoodsCheck(@Field("productIds") String pids, @Field("isChecked") int isChecked);

    //更新商品的数据
    @POST("cart/update")
    @FormUrlEncoded
    Flowable<CartGoodsUpdateBean> updateCartGoods(@Field("productId") String pids, @Field("goodsId") String goodsId, @Field("number") int number, @Field("id") int id);


    //删除商品
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<CartGoodsDeleteBean> deleteCartGoods(@Field("productIds") String pids);

}
