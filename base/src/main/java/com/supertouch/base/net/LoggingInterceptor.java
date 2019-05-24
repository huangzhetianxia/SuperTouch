package com.supertouch.base.net;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 数据拦截器
 *
 * @author huanghusheng
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        String method = request.method();
        JSONObject jsonObject = new JSONObject();
        if ("POST".equals(method) || "PUT".equals(method)) {
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                if (body != null) {
                    for (int i = 0; i < body.size(); i++) {
                        try {
                            jsonObject.put(body.name(i), body.encodedValue(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.e("request", String.format("发送请求 %s%nRequestParams:%s%nMethod:%s",
                        request.url(), jsonObject.toString(), request.method()));
            } else {
                Buffer buffer = new Buffer();
                RequestBody requestBody = request.body();
                if (requestBody != null) {
                    request.body().writeTo(buffer);
                    String body = buffer.readUtf8();
                    Log.e("request", String.format("发送请求 %s%nRequestParams:%s%nMethod:%s",
                            request.url(), body, request.method()));
                }
            }
        } else {
            Log.e("request", String.format("发送请求 %s%nMethod:%s",
                    request.url(),request.method()));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        Log.e("request",
                String.format("接收响应: %s %n返回json:%n%s %n耗时：%.1fms",
                        response.request().url(),
                        JsonFormat.format(responseBody.string()),
                        (t2 - t1) / 1e6d
                ));
        return response;
    }

}
