package com.example.dianshangxiangmu;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.base.BaseActivity;
import com.example.dianshangxiangmu.bean.RelatedBean;
import com.example.dianshangxiangmu.contract.PurchaseContract;
import com.example.dianshangxiangmu.presenter.PurchasePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PurchaseActivity extends BaseActivity<PurchaseContract.View, PurchaseContract.Presenter> implements PurchaseContract.View {

    private TextView mTitleTxt;
    private TextView mDesTxt;
    private TextView mPriceTxt;
    private TextView mNumsTxt;
    private ConstraintLayout mNumsLayout;
    private TextView mParamTxt;
    private TextView mMetarialTxt;
    private ConstraintLayout mMetarialLayout;
    private TextView mSizeTxt;
    private ConstraintLayout mSizeLayout;
    private TextView mColorTxt;
    private ConstraintLayout mColorLayout;
    private TextView mNormTxt;
    private ConstraintLayout mNormLayout;
    private TextView mPlaceTxt;
    private WebView mMyWebView;
    private ConstraintLayout mQuestionTxt;
    private RecyclerView mRecyclerView;
    private TextView mCollectTxt;
    private TextView mCartTxt;
    private TextView mBuyTxt;
    private TextView mAddCartTxt;
    private Banner mMyBanner;
    private ConstraintLayout mContent;

    @Override
    protected void initData() {
        int relatedId = getIntent().getIntExtra("id", 0);
        presenter.getRelatedData(relatedId);
    }

    @Override
    protected PurchasePresenter initPresenter() {
        return new PurchasePresenter();
    }

    @Override
    protected void initView() {

        mTitleTxt = (TextView) findViewById(R.id.txt_title);
        mDesTxt = (TextView) findViewById(R.id.txt_des);
        mPriceTxt = (TextView) findViewById(R.id.txt_price);
        mNumsTxt = (TextView) findViewById(R.id.txt_nums);
        mNumsLayout = (ConstraintLayout) findViewById(R.id.layout_nums);
        mParamTxt = (TextView) findViewById(R.id.txt_param);
        mMetarialTxt = (TextView) findViewById(R.id.txt_metarial);
        mMetarialLayout = (ConstraintLayout) findViewById(R.id.layout_metarial);
        mSizeTxt = (TextView) findViewById(R.id.txt_size);
        mSizeLayout = (ConstraintLayout) findViewById(R.id.layout_size);
        mColorTxt = (TextView) findViewById(R.id.txt_color);
        mColorLayout = (ConstraintLayout) findViewById(R.id.layout_color);
        mNormTxt = (TextView) findViewById(R.id.txt_norm);
        mNormLayout = (ConstraintLayout) findViewById(R.id.layout_norm);
        mPlaceTxt = (TextView) findViewById(R.id.txt_place);
        mMyWebView = (WebView) findViewById(R.id.myWebView);
        mQuestionTxt = (ConstraintLayout) findViewById(R.id.txt_question);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mCollectTxt = (TextView) findViewById(R.id.txt_collect);
        mCartTxt = (TextView) findViewById(R.id.txt_cart);
        mBuyTxt = (TextView) findViewById(R.id.txt_buy);
        mAddCartTxt = (TextView) findViewById(R.id.txt_addCart);
        mMyBanner = (Banner) findViewById(R.id.myBanner);
        mContent = (ConstraintLayout) findViewById(R.id.content);
        WebSettings webSettings = mMyWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mAddCartTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View item= LayoutInflater.from(PurchaseActivity.this).inflate(R.layout.pop_item,null);
                final PopupWindow popupWindow = new PopupWindow(item,getWindowManager().getDefaultDisplay().getWidth(),700);
                popupWindow.showAtLocation(mContent, Gravity.BOTTOM,0,0);
                ImageView img = item.findViewById(R.id.img);
                TextView jia = item.findViewById(R.id.jia);
                TextView jian = item.findViewById(R.id.jian);
                ImageView image = item.findViewById(R.id.image);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        mCollectTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase;
    }

    @Override
    public void getRelatedDataReturn(RelatedBean result) {
        updateBanner(result.getData().getGallery());

        String price = getResources().getString(R.string.price_news_model).replace("$", String.valueOf(result.getData().getInfo().getRetail_price()));
        updatePrice(result.getData().getInfo().getName(),
                result.getData().getInfo().getGoods_brief(), price);

        updateWebView(result.getData().getInfo());
    }

    //填充banner数据
    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> list) {
        List<String> listBanner = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String img_url = list.get(i).getImg_url();
            listBanner.add(img_url);
        }
        mMyBanner.setImages(listBanner).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        }).start();
    }

    //填充信息数据
    private void updatePrice(String name, String des, String price) {
        mTitleTxt.setText(name);
        mDesTxt.setText(des);
        mPriceTxt.setText(price);
    }

    //填充规格数据
    private void updateParam() {
    }

    //商品介绍描述信息
    private void updateWebView(RelatedBean.DataBeanX.InfoBean infoBean) {

        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>" + css_str + "</style></head><body>");
        sb.append(infoBean.getGoods_desc() + "</body></html>");
        mMyWebView.loadData(sb.toString(), "text/html", "utf-8");
    }
}
