package com.example.dianshangxiangmu.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dianshangxiangmu.LoginActivity;
import com.example.dianshangxiangmu.R;
import com.example.dianshangxiangmu.adapter.ShoppingAdapter;
import com.example.dianshangxiangmu.base.BaseAdapter;
import com.example.dianshangxiangmu.base.BaseFragment;
import com.example.dianshangxiangmu.bean.CartBean;
import com.example.dianshangxiangmu.bean.CartGoodsCheckBean;
import com.example.dianshangxiangmu.bean.CartGoodsDeleteBean;
import com.example.dianshangxiangmu.bean.CartGoodsUpdateBean;
import com.example.dianshangxiangmu.contract.ShoppingContract;
import com.example.dianshangxiangmu.presenter.ShoppingPresenter;
import com.example.dianshangxiangmu.utils.SpUtils;
import com.example.dianshangxiangmu.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends BaseFragment<ShoppingContract.View, ShoppingContract.Presenter> implements ShoppingContract.View, View.OnClickListener,BaseAdapter.OnClickItem {
    private RecyclerView mListCart;
    private CheckBox mAllRadio;
    private TextView mTotalPriceTxt;
    private TextView mOrderTxt;
    private TextView mEditTxt;
    private ConstraintLayout mCommonLayout;

    ShoppingAdapter shoppingAdapter;
    List<CartBean.DataBean.CartListBean> list;

    @Override
    protected void initData() {
        //如果用户没有登录就要
        String token = SpUtils.getInstance().getString("token");
        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, 100);
        } else {
            presenter.getCartIndex();
        }
    }

    @Override
    protected ShoppingContract.Presenter createPresenter() {
        return new ShoppingPresenter();
    }

    @Override
    protected void initView(View view) {

        mListCart = (RecyclerView) view.findViewById(R.id.cart_list);
        mAllRadio = (CheckBox) view.findViewById(R.id.radio_all);
        mAllRadio.setOnClickListener(this);
        mTotalPriceTxt = (TextView) view.findViewById(R.id.txt_TotalPrice);
        mOrderTxt = (TextView) view.findViewById(R.id.txt_order);
        mOrderTxt.setOnClickListener(this);
        mEditTxt = (TextView) view.findViewById(R.id.txt_edit);
        mEditTxt.setOnClickListener(this);
        mCommonLayout = (ConstraintLayout) view.findViewById(R.id.layout_common);

        list = new ArrayList<>();
        shoppingAdapter = new ShoppingAdapter(list);
        mListCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListCart.setAdapter(shoppingAdapter);
        shoppingAdapter.setOnItemClick(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.shopping_fragment;
    }

    @Override
    public void getCartIndexReturn(CartBean result) {
        shoppingAdapter.addData(result.getData().getCartList());
        //判断当前类别的数据是否是全部选中
        int totalPrice = 0;
        int nums = 0;
        boolean isSelectAll = true;
        for (CartBean.DataBean.CartListBean item : result.getData().getCartList()) {
            if (isSelectAll) {
                if (!item.isSelect) {
                    isSelectAll = false;
                }
            }
            if (item.isSelect) {
                totalPrice += item.getRetail_price() * item.getNumber();
                nums += item.getNumber();
            }
        }
        if (isSelectAll) {
            mAllRadio.setChecked(true);
        }
        String price = getActivity().getResources().getString(R.string.price_news_model).replace("$", String.valueOf(totalPrice));
        mTotalPriceTxt.setText(price);
        mAllRadio.setText("全选(" + nums + ")");
    }

    @Override
    public void setCartGoodsCheckedReturn(CartGoodsCheckBean result) {

    }

    @Override
    public void updateCartGoodsReturn(CartGoodsUpdateBean result) {
        for (CartGoodsUpdateBean.DataBean.CartListBean item : result.getData().getCartList()) {
            CartBean.DataBean.CartListBean bean = getItemDataById(item.getId());
            if (bean != null) bean.setNumber(item.getNumber());
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteCartGoodsReturn(CartGoodsDeleteBean result) {
        // int lg = list.size();
        for (int i = 0; i < list.size(); i++) {
            CartBean.DataBean.CartListBean listBean = list.get(i);
            boolean bool = false; //检验当前的list中第i条数据是否被删除
            for (CartGoodsDeleteBean.DataBean.CartListBean item : result.getData().getCartList()) {
                if (item.getId() == listBean.getId()) {
                    bool = true;
                    break;
                }
            }
            //如果不在，删除list中的第i条数据
            if (!bool) {
                list.remove(i);
            }
        }
        shoppingAdapter.notifyDataSetChanged();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == 100) {
            if (presenter != null) presenter.getCartIndex();
        }
    }

    /**
     * 通过商品列表数据id获取对应的商品数据
     *
     * @param id
     * @return
     */
    private CartBean.DataBean.CartListBean getItemDataById(int id) {
        for (CartBean.DataBean.CartListBean item : list) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_all:
                boolean isChecked = mAllRadio.isChecked();
                setSelectAll(isChecked);
                shoppingAdapter.notifyDataSetChanged();
                break;
            case R.id.txt_order:
                boolean isEditor = getPageIsEditor();
                if(isEditor){
                    //当前是编辑状态，执行删除操作
                    deleteGoods();
                }else{
                    //当前是正常状态，执行下单的操作
                    doOrder();
                }
                break;
            case R.id.txt_edit:
                // TODO 20/03/08
                break;
            default:
                break;
        }
    }

    private void doOrder() {

    }

    private void deleteGoods() {
        //查找当前需要删除的商品
        StringBuilder sb = new StringBuilder();
        for(CartBean.DataBean.CartListBean item:list){
            if(item.isDelSelect){
                sb.append(item.getProduct_id());
                sb.append(",");
            }
        }
        if(sb.length() > 0){
            //去掉末尾的逗号
            sb.deleteCharAt(sb.length()-1);
            String pids = sb.toString();
            //调用删除商品的接口
            presenter.deleteCartGoods(pids);
        }else{
            Toast.makeText(getActivity(), "没有选中任何商品", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSelectAll(boolean bool) {
        //更新列表中商品的状态
        int totalPrice = 0;
        int nums = 0;
        int[] ids = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            list.get(i).isSelect = bool;
            ids[i] = list.get(i).getId();
            if(bool){
                totalPrice += list.get(i).getRetail_price()*list.get(i).getNumber();
                nums += list.get(i).getNumber();
            }
        }
        int isChecked = bool ? 0 : 1;
        updateGoodsChecked(ids,isChecked);
        //刷新界面数量和总价
        if(bool){
            String  price = getActivity().getResources().getString(R.string.price_news_model).replace("$",String.valueOf(totalPrice));
            mTotalPriceTxt.setText(price);
            mAllRadio.setText("全选("+nums+")");
        }else{
            mTotalPriceTxt.setText("");
        }
    }

    private void updateGoodsChecked(int[] ids, int isChecked) {
        String pids = StringUtils.splitArray(ids);
        presenter.setCartGoodsChecked(pids,isChecked);
    }

    /**
     * 获取当前页面是否是编辑状态
     * @return
     */
    private boolean getPageIsEditor(){
        String str = mEditTxt.getText().toString();
        return str.equals("编辑") ? false : true;
    }

    @Override
    public void itenClick(BaseAdapter.BaseViewHolder v, int pos) {

    }
}
