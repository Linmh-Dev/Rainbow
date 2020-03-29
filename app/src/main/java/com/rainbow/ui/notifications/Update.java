package com.rainbow.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.rainbow.MyApplication;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Update extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient httpClient=new OkHttpClient();
        Request request=new Request.Builder().url("http://47.115.23.162/update.txt").build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();}
        catch (Exception e){
            e.printStackTrace();
            Log.d("linm", "erro");
        }
        try {
            String string = response.body().string();
            SharedPreferences version = MyApplication.getContext().getSharedPreferences("version",Context.MODE_PRIVATE);
            version.edit().putFloat("version",Float.valueOf(string)).apply();
            Log.d("linm", string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       float string = 0;
        try {
            SharedPreferences version = MyApplication.getContext().getSharedPreferences("version", Context.MODE_PRIVATE);
            string = version.getFloat("version", 0);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (string==2.0){
            //没有新版本
            Toast.makeText(MyApplication.getContext(),"已经是最新版本！",Toast.LENGTH_SHORT).show();
        }else if (string>2.0){
            //有新版本
            Toast.makeText(MyApplication.getContext(),"发现新版本！",Toast.LENGTH_SHORT).show();
          //  dowload();
        }else if (string==0){
            Toast.makeText(MyApplication.getContext(),"发生错误！",Toast.LENGTH_SHORT).show();
        }
    }
}
