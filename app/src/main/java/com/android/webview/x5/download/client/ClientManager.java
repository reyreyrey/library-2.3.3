package com.android.webview.x5.download.client;

import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;


/**
 * author: Rea.X
 * date: 2017/7/19.
 */

public class ClientManager {

    private static OkHttpClient defaultClient, checkDomainClient, downloadClient;

    private static OkHttpClient getOkHttpClient(long timeout, boolean isDownload) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor("HttpLog");
        interceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(interceptor);
        builder.followRedirects(isDownload);
        builder.followSslRedirects(isDownload);
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return builder.build();
    }


    public static OkHttpClient getDefaultHttpClient() {
        if (defaultClient == null)
            defaultClient = getOkHttpClient(30 * 1000, false);
        return defaultClient;
    }

    public static OkHttpClient getDownloadClient() {
        downloadClient = getOkHttpClient(30 * 1000, true);
        return downloadClient;
    }
}
