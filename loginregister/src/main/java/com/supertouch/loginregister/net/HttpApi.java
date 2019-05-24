package com.supertouch.loginregister.net;

import com.supertouch.loginregister.bean.ResLoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 接口API
 */
public interface HttpApi {

    /**
     * 登录
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResLoginBean> login(@FieldMap Map<String,String> map);



}
