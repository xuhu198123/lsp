package com.wulianwang.lsp.util;

import android.util.Log;

import com.wulianwang.lsp.bean.ProgressRequestBody;
import com.wulianwang.lsp.listener.ProgressListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class                    HttpUtil {
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(10000, TimeUnit.MILLISECONDS).build();

    /**
     * get请求
     * @param url
     * @param callack
     *
     * e.g
     * String url = "http://192.168.1.100:8080?username=admin&password=123456";
     * HttpUtil.get(url, new Callback() {
     *     @Override
     *     public void onFailure(Call call, IOException e) {
     *     //请求失败后执行的代码
         *     e.printStackTrace();
     *     }
     *     @Override
     *     public void onResponse(Call call, Response response) throws IOException {
 *         //请求成功后执行的代码
     *     }
     * });
     */
    public static void get(String url, okhttp3.Callback callack) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callack);
    }

    /**
     * post请求
     * @param url
     * @param listener 发送文件时的上传进度，不发送文件时为null
     * @param callback
     * @param files 发送的文件数组，不发送文件时为null
     * @param map 发送的数据，不发送数据时为null
     *
     *  e.g
     *      String url = "http://192.168.1.100:8080";
     *      HttpUtil.post(url, new ProgressListener() {
     *             @Override
     *             public void onProgress(long currentBytes, long contentLength, boolean done) {
     *
     *             }
     *         }, new Callback() {
     *             @Override
     *             public void onFailure(@NotNull Call call, @NotNull IOException e) {
     *
     *             }
     *
     *             @Override
     *             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
     *
     *             }
     *         }, files, map);
     */
    public static void post(String url, final ProgressListener listener, okhttp3.Callback callback, File[] files, Map<String, String> map){

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //第一个参数要与Servlet中的一致
        if(files != null){
            for (File file : files) {
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }

        if (map != null) {
            for (String key : map.keySet()) {
                builder.addFormDataPart(key, map.get(key));
            }
        }

        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder().url(url).post(new ProgressRequestBody(multipartBody, listener)).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
