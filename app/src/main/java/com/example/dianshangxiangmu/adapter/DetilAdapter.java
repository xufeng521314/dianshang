package com.example.dianshangxiangmu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.DetilListBean;

import java.util.List;

public class DetilAdapter extends BaseAdapter {
    public DetilAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.detil_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.detilImg);
        TextView title = (TextView) holder.getView(R.id.detilTitle);
        TextView price = (TextView) holder.getView(R.id.detilPrice);
        DetilListBean.DataBeanX.GoodsListBean list = (DetilListBean.DataBeanX.GoodsListBean) this.list.get(position);
        Glide.with(context).load(list.getList_pic_url()).into(img);
        title.setText(list.getName());
        price.setText(list.getRetail_price()+"元起");
    }
}
