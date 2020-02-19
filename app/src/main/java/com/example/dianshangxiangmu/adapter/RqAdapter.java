package com.example.dianshangxiangmu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.IndexBean;

import java.util.List;

public class RqAdapter extends BaseAdapter {
    public RqAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.rq_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.rqImg);
        TextView title = (TextView) holder.getView(R.id.rqTitle);
        TextView type = (TextView) holder.getView(R.id.rqType);
        TextView price = (TextView) holder.getView(R.id.rqPrice);
        IndexBean.DataBean.HotGoodsListBean listBean = (IndexBean.DataBean.HotGoodsListBean) list.get(position);
        Glide.with(context).load(listBean.getList_pic_url()).into(img);
        title.setText(listBean.getName());
        type.setText(listBean.getGoods_brief());
        price.setText("￥"+listBean.getRetail_price());
    }
}
