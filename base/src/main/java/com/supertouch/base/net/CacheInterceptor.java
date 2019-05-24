package com.supertouch.base.net;

import com.supertouch.base.BaseApplication;
import com.supertouch.base.util.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 *
 * @author huanghusheng
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.isNetworkConnected(BaseApplication.context)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(request);
        if (NetUtils.isNetworkConnected(BaseApplication.context)) {
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder().header("Cache-Control", cacheControl)
                    .removeHeader("Pragma").build();
        } else {
            return response.newBuilder().header("Cache-Control", "public, " +
                    "only-if-cached, max-stale=" + "60 * 60 * 24 * 7")
                    .removeHeader("Pragma").build();
        }
    }
}
