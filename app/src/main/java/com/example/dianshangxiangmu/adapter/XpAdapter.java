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

public class XpAdapter extends BaseAdapter {
    public XpAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.xp_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.xpImg);
        TextView title = (TextView) holder.getView(R.id.xpTitle);
        TextView price = (TextView) holder.getView(R.id.xpPrice);
        IndexBean.DataBean.NewGoodsListBean bean = (IndexBean.DataBean.NewGoodsListBean) list.get(position);
        Glide.with(context).load(bean.getList_pic_url()).into(img);
        title.setText(bean.getName());
        price.setText("￥"+bean.getRetail_price());
    }
}
