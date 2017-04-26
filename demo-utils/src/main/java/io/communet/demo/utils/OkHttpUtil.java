package io.communet.demo.utils;

import com.google.common.base.Throwables;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 17/3/13
 * <p>Version: 1.0
 */
public class OkHttpUtil {
    private static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3,TimeUnit.SECONDS).build();

    public static String get(String url)  {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return resutl(request);
    }

    public static String upload(byte[] content, String uploadUrl) throws IOException {
        logger.info("OkHttpUtil.upload url : " + uploadUrl);
        RequestBody fileBody = RequestBody.create(MediaType.parse("text/xml "), content);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", "content", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(uploadUrl)
                .post(requestBody)
                .build();
        Response response;
        response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String upload(File file, String uploadUrl) throws IOException {
        logger.info("OkHttpUtil.upload url : " + uploadUrl);
        RequestBody fileBody = RequestBody.create(MediaType.parse("text/xml "), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(uploadUrl)
                .post(requestBody)
                .build();
        Response response;
        response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postJson(String url, String json)  {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return resutl(request);
    }

    public static String postForm(String url,Map<String,String> params,Headers headers){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .headers(headers)
                .build();
        return postForm(url,params,request);
    }

    public static String postForm(String url,Map<String,String> params,String cookie){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .addHeader("Cookie",cookie)
                .build();
        return postForm(url,params,request);
    }

    public static String postForm(String url,Map<String,String> params){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return postForm(url,params,request);
    }

    public static String postForm(String url,Map<String,String> params,Request request){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
       return resutl(request);
    }

    private static String resutl(Request request){
        String resutl = null;
        try{
            Response response = client.newCall(request).execute();
            resutl = response.body().string();
        }catch (IOException ioe){
            logger.error(Throwables.getStackTraceAsString(ioe));
        }
        return resutl;
    }

}
