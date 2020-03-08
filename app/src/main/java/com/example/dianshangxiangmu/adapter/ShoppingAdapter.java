package com.example.dianshangxiangmu.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.bean.CartBean;

import java.util.List;

public class ShoppingAdapter extends BaseAdapter {

    public boolean isEdit; //是否是编辑状态

    public ShoppingAdapter(List list) {
        super(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.shopping_item;
    }

    @Override
    protected void bindData(final BaseViewHolder holder, int position, Object o) {
        ConstraintLayout layout_default = (ConstraintLayout) holder.getView(R.id.layout_default);
        ConstraintLayout layout_edit = (ConstraintLayout) holder.getView(R.id.layout_edit);
        layout_default.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        layout_edit.setVisibility(isEdit ? View.VISIBLE : View.GONE);

        final CartBean.DataBean.CartListBean listBean = (CartBean.DataBean.CartListBean) o;
        final CheckBox radio_select = (CheckBox) holder.getView(R.id.radio_select);
        ImageView img_item = (ImageView) holder.getView(R.id.img_item);



        //默认布局
        TextView txt_name = (TextView) holder.getView(R.id.txt_name);
        TextView txt_price = (TextView) holder.getView(R.id.txt_price);
        TextView txt_nums = (TextView) holder.getView(R.id.txt_nums);
        //编辑布局
        TextView txt_edit_price = (TextView) holder.getView(R.id.txt_edit_price);
        TextView txt_subtract = (TextView) holder.getView(R.id.txt_subtract);
        TextView txt_add = (TextView) holder.getView(R.id.txt_add);
        TextView txt_edit_nums = (TextView) holder.getView(R.id.txt_edit_nums);

        Glide.with(context).load(listBean.getList_pic_url()).into(img_item);

        radio_select.setTag(o);
        radio_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartBean.DataBean.CartListBean bean = (CartBean.DataBean.CartListBean) v.getTag();
                boolean isChecked = radio_select.isChecked();
                if(!isEdit){
                    bean.isSelect = isChecked;
                    if(onClickItem != null){
                        onClickItem.itenClick(holder, holder.getLayoutPosition());
                    }
                }else{
                    bean.isDelSelect = isChecked;
                }
            }
        });

        if(!isEdit){
            //正常状态下显示的item
            radio_select.setChecked(listBean.isSelect);
            txt_name.setText(listBean.getGoods_name());
            String price = context.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(listBean.getRetail_price()));
            txt_price.setText(price);
            txt_nums.setText(String.valueOf(listBean.getNumber()));

        }else{
            //编辑状态下显示的item
            radio_select.setChecked(false);
            String price = context.getResources().getString(R.string.price_news_model);
            price = price.replace("$",String.valueOf(listBean.getRetail_price()));
            txt_edit_price.setText(price);
            txt_edit_nums.setText(String.valueOf(listBean.getNumber()));
            txt_subtract.setTag(holder);
            txt_subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listBean.getNumber() == 1) return;
                    listBean.setNumber(listBean.getNumber()-1);
                    //txt_edit_nums.setText(String.valueOf(listBean.getNumber()));
                    //更新数据
                    BaseViewHolder vh = (BaseViewHolder) v.getTag();
                    int pos = vh.getLayoutPosition();
                    if(onClickItem != null){
                        onClickItem.itenClick(vh,pos);
                    }
                }
            });
            txt_add.setTag(holder);
            txt_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listBean.setNumber(listBean.getNumber()+1);
                    //txt_edit_nums.setText(String.valueOf(listBean.getNumber()));
                    //更新数据
                    BaseViewHolder vh = (BaseViewHolder) v.getTag();
                    int pos = vh.getLayoutPosition();
                    if(onClickItem != null){
                        onClickItem.itenClick(vh,pos);
                    }
                }
            });
        }
    }
}
