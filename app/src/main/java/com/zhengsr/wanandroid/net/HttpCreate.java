package com.zhengsr.wanandroid.net;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.zhengsr.wanandroid.MainApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author by  zhengshaorui on 2019/10/9
 * Describe: 网络生成类
 */
public class HttpCreate {

    public static HttpServerApi getServer(){
        Retrofit retrofit = new Retrofit.Builder()
                //这里采用这个，因为有多个baseurl
                .baseUrl("https://api.github.com/")
                //转字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                //fastjson
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.BUILDER)
                .build();
        return retrofit.create(HttpServerApi.class);
    }

    /**
     * 配置okhttp3 client
     */
    static ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(),
                    new SharedPrefsCookiePersistor(MainApplication.INSTANCE));
    private static class OkHttpHolder{
        static OkHttpClient BUILDER = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cookieJar(cookieJar)
                .build();
    }
}
