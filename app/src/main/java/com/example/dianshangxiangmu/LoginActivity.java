package com.example.dianshangxiangmu;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dianshangxiangmu.base.BaseActivity;
import com.example.dianshangxiangmu.bean.UserBean;
import com.example.dianshangxiangmu.contract.LoginContract;
import com.example.dianshangxiangmu.presenter.LoginPresenter;
import com.example.dianshangxiangmu.utils.SpUtils;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private Button mLoginBtn;
    private TextView mLogenBtn;

    @Override
    protected void initData() {

    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

        mUsernameEdit = (EditText) findViewById(R.id.edit_username);
        mPasswordEdit = (EditText) findViewById(R.id.edit_password);
        mLoginBtn = (Button) findViewById(R.id.btn_login);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                presenter.login(nickname, password);
                Intent intent = new Intent(LoginActivity.this, PurchaseActivity.class);
                startActivity(intent);
            }
        });
        mLogenBtn = (TextView) findViewById(R.id.btn_logen);
        mLogenBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginReturn(UserBean result) {
        //登录成功以后存入sp
        SpUtils.getInstance().setValue("token", result.getData().getToken());
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logen:
                Intent intent = new Intent(LoginActivity.this, LogenActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
