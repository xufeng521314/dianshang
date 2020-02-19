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

public class JxAdapter extends BaseAdapter {
    public JxAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.jx_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.jxImg);
        TextView title = (TextView) holder.getView(R.id.jxTitle);
        TextView type = (TextView) holder.getView(R.id.jxType);
        IndexBean.DataBean.TopicListBean been = (IndexBean.DataBean.TopicListBean) list.get(position);
        Glide.with(context).load(been.getItem_pic_url()).into(img);
        title.setText(been.getTitle()+"￥ 0.00元起");
        type.setText(been.getSubtitle());
    }
}
