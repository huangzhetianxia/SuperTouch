package com.supertouch.loginregister.net;


import com.supertouch.base.net.BaseConstant;
import com.supertouch.base.net.RetrofitManager;
import com.supertouch.loginregister.bean.ResLoginBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NetRequest {

    /**
     * 登录
     * @param username
     * @param password
     */
    public static void requestLogin(String username, String password) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        HttpApi httpApi = RetrofitManager.getInstance(BaseConstant.BASE_URL).setCreate(HttpApi.class);
        final Observable<ResLoginBean> requestData = httpApi.login(hashMap);
        requestData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResLoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResLoginBean resLoginBean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
