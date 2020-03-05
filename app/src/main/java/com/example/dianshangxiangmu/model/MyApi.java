package com.example.dianshangxiangmu.model;




import com.example.dianshangxiangmu.bean.CatalogListBean;
import com.example.dianshangxiangmu.bean.CatalogTabBean;
import com.example.dianshangxiangmu.bean.DetilListBean;
import com.example.dianshangxiangmu.bean.DetilTabBean;
import com.example.dianshangxiangmu.bean.IndexBean;
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

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserBean> login(@Field("nickname") String nickname, @Field("password") String password);

}
