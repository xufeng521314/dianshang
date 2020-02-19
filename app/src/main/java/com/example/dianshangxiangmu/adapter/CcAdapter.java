package com.example.dianshangxiangmu.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.IndexBean;

import java.util.List;

public class CcAdapter extends BaseAdapter {
    public CcAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.cc_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.ccImg);
        TextView title = (TextView) holder.getView(R.id.ccTitle);
        TextView price = (TextView) holder.getView(R.id.ccPrice);
        IndexBean.DataBean.CategoryListBean.GoodsListBean list = (IndexBean.DataBean.CategoryListBean.GoodsListBean) this.list.get(position);
        Glide.with(context).load(list.getList_pic_url()).into(img);
        title.setText(list.getName());
        price.setText("￥"+(String) list.getRetail_price());
    }
}
