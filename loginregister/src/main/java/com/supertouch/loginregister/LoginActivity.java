package com.supertouch.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.supertouch.ForgetPasswordActivity;
import com.supertouch.loginregister.net.NetRequest;
import com.supertouch.loginregister.util.FormatCheckUtils;
import com.supertouch.loginregister.util.MD5Utils;


/**
 * 登录页面
 */
@Route(path = "/login/loginPage")
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accountEt, passwordEt;
    private Button loginBtn;
    private TextView forgetPassWardTv,registerTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEt = findViewById(R.id.et_account);
        passwordEt = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        forgetPassWardTv = findViewById(R.id.tv_forgot_password);
        forgetPassWardTv.setOnClickListener(this);
        registerTv = findViewById(R.id.tv_register);
        registerTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_login) {// 登录
            login();

        } else if (i == R.id.tv_forgot_password) {
            Log.i("info", "onClick: ");
            ARouter.getInstance().build("/login/forgetPassword")
                    .navigation();


        } else if (i == R.id.tv_register) {
            startActivity(new Intent(this, RegisterActivity.class));

        }
    }

    /**
     * 用户登录
     */
    private void login() {
        String account = accountEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            // 请输入账号
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            // 请输入密码
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!FormatCheckUtils.isTelPhoneNumber(account)){
            // 请输入正确的手机号码
            Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!FormatCheckUtils.isPasswordFormat(password)){
            Toast.makeText(this,"请输入8-16位密码",Toast.LENGTH_SHORT).show();
            return;
        }
        // 网络请求-登录
        requestLogin(account, MD5Utils.getMD5Code(password));
    }

    /**
     * 网络请求-登录
     */
    private void requestLogin(String account,String password){
        NetRequest.requestLogin(account,password);
    }

}
