package com.example.dianshangxiangmu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.CatalogItem;
import com.example.dianshangxiangmu.bean.CatalogListBean;

import java.util.List;

public class FenLeiAdapter extends BaseAdapter {
    public FenLeiAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.fenlei_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        ImageView img = (ImageView) holder.getView(R.id.fenleiImg);
        TextView title = (TextView) holder.getView(R.id.fenleiTitle);
        CatalogItem list = (CatalogItem) this.list.get(position);
        Glide.with(context).load(list.img).into(img);
        title.setText(list.name);
    }
}
