package com.supertouch.base.net;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit网络请求管理器
 *
 * @author huanghusheng
 */
public class RetrofitManager {

    private Retrofit mRetrofit;
    private static RetrofitManager mRetrofitManager;
    private static OkHttpClient.Builder builder;

    static {
        getOkHttpClientBuilder();
    }

    private RetrofitManager(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }

    public static RetrofitManager getInstance(String baseUrl) {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager(baseUrl);
                }
            }
        }
        return mRetrofitManager;
    }


    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        if (builder == null) {
            synchronized (OkHttpClient.Builder.class) {
                if (builder == null) {
                    builder = new OkHttpClient.Builder();
                    // http头部参数，可在拦截器HeaderInterceptor的intercept方法进行动态参数值设置
                    HeaderInterceptor headerInterceptor = new HeaderInterceptor.Builder().build();
                    builder.addInterceptor(headerInterceptor);
                    builder.addInterceptor(new LoggingInterceptor());
                    // 根据项目自身需求是否启用缓存起
                    // builder.addNetworkInterceptor(new CacheInterceptor());//网络拦截器
                    // builder.cache(mCache);// 设置缓存
                    builder.connectTimeout(60, TimeUnit.SECONDS);// 设置连接超时时间
                    builder.readTimeout(60, TimeUnit.SECONDS);   // 设置读取超时时间
                    builder.writeTimeout(60, TimeUnit.SECONDS);  // 设置写入超时时间
                }
            }
        }
        return builder;
    }

    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }

    /**
     * 实现https请求
     * @description no-use
     */
    private static SSLSocketFactory getSSLSocketFactory(Context context, String name) {


        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        InputStream inputStream = null;
        Certificate certificate;

        try {
            inputStream = context.getResources().getAssets().open(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            certificateFactory = CertificateFactory.getInstance("X.509");
            certificate = certificateFactory.generateCertificate(inputStream);

            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry(name, certificate);

            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }
}
