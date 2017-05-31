package com.cnmar.benxiao.retrofit;


import com.cnmar.benxiao.utils.UrlHelper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Jam on 16-5-17
 * Description:
 */
public class Api {
    private static ApiService SERVICE;

    public static ApiService getDefault() {
        if (SERVICE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
            SERVICE = new Retrofit.Builder()
                    .baseUrl(UrlHelper.URL_BASE)
//                   因为后台用的FastJson解析，所以Retrofit解析用的FastJson
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }

}
