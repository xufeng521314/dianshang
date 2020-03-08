package com.example.dianshangxiangmu;

import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dianshangxiangmu.base.BaseActivity;
import com.example.dianshangxiangmu.base.IPresenter;
import com.example.dianshangxiangmu.fragment.FenLeiFragment;
import com.example.dianshangxiangmu.fragment.HomeFragment;
import com.example.dianshangxiangmu.fragment.OwnFragment;
import com.example.dianshangxiangmu.fragment.ShoppingFragment;
import com.example.dianshangxiangmu.fragment.ZhuanTiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    private FrameLayout mFrame;
    private BottomNavigationView mNavigationview;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private ZhuanTiFragment zhuanTiFragment;
    private FenLeiFragment fenLeiFragment;
    private ShoppingFragment shoppingFragment;
    private OwnFragment ownFragment;

    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        zhuanTiFragment = new ZhuanTiFragment();
        fenLeiFragment = new FenLeiFragment();
        shoppingFragment = new ShoppingFragment();
        ownFragment = new OwnFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame,homeFragment).add(R.id.frame,zhuanTiFragment)
                .add(R.id.frame,fenLeiFragment).show(homeFragment).hide(zhuanTiFragment).hide(fenLeiFragment)
                .commit();

        mNavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.home:
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.show(homeFragment).hide(zhuanTiFragment).hide(fenLeiFragment)
                                .hide(shoppingFragment).commit();
                        break;
                    case R.id.zhuanti:
                        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                        fragmentTransaction2.show(zhuanTiFragment).hide(homeFragment).hide(fenLeiFragment)
                                .hide(shoppingFragment).commit();
                        break;
                    case R.id.fenlei:
                        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                        fragmentTransaction3.show(fenLeiFragment).hide(homeFragment).hide(zhuanTiFragment)
                                .hide(shoppingFragment).commit();
//                    case R.id.shopping:
//                        FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
//                        fragmentTransaction4.show(shoppingFragment).hide(homeFragment).hide(zhuanTiFragment)
//                                .hide(fenLeiFragment).commit();
//                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mNavigationview = (BottomNavigationView) findViewById(R.id.navigationview);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
