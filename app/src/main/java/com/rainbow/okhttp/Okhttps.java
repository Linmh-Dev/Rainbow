package com.rainbow.okhttp;

import android.os.AsyncTask;
import android.util.Log;

import com.rainbow.gson.WordGson;
import com.rainbow.room.Insert;
import com.rainbow.room.Word;
import com.rainbow.room.WordDao;
import com.rainbow.room.WordDatabases;
import com.rainbow.viewmodel.WordViewModel;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Okhttps extends AsyncTask<String, Void, String> {


    @Override

    protected String doInBackground(String... strings) {
        String query = strings[0];
        Date date = new Date();

        long time = date.getTime();
        long salt = time + 10 * time;
        long ts = time;
        String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";
        String sign = "";
        String bv = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(User_Agent.getBytes());
            bv = gethex(digest);
            String saltss = String.valueOf(salt);
            sign = "fanyideskweb" + query + saltss + "Nw(nmmbP%A-r6U3EUn]Aj";
            sign = gethex(md5.digest(sign.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("i", query)
                .add("client", "fanyideskweb")
                .add("from", "AUTO")
                .add("to", "AUTO")
                .add("smartresult", "dict")
                .add("doctype", "json")
                .add("version", "2.1")
                .add("keyfrom", "fanyi.web")
                .add("action", "FY_BY_REALTlME")
                .add("salt", String.valueOf(salt))
                .add("sign", sign)
                .add("ts", String.valueOf(ts))
                .add("bv", bv)
                .build();
        Request request = new Request.Builder().url("http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule")
                .post(requestBody)
                .header("Cookie", "OUTFOX_SEARCH_USER_ID=1474458966@10.169.0.102; JSESSIONID=aaaKVlv4kCidg5fNvLaex; OUTFOX_SEARCH_USER_ID_NCOO=1264177066.6226926; ___rl__test__cookies=1584852732402")
                .header("Referer", "http://fanyi.youdao.com/")
                .header("User-Agent", User_Agent)
                .build();
        Response executea = null;
        try {
            executea = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responsed = null;
        try {
            responsed = executea.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return soleString(responsed);

    }

    @Override
    protected void onPostExecute(String s) {
        WordDao wordDao= WordDatabases.getWordDatabases().getDao();
        WordGson wordGson = new WordGson(s);
        String query=wordGson.getQuery();
        String result = wordGson.getResult();
        Word word=new Word(query,result);
        WordViewModel.setWordMutableLiveData(word);
       new Insert().execute(word);
    }


    private String gethex(byte[] bytes) {
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    private String soleString(String string) {
        char[] chars = string.toCharArray();
        int flag = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[' && chars[i + 1] == '[' && chars[i + 2] == '{' && flag == 0) {
                flag = i + 2;
            }
        }
        String ssr = string.substring(flag, chars.length);
        System.out.println(ssr);
        int i = ssr.lastIndexOf("]]");
        ssr = ssr.substring(0, i);
        return ssr;
    }

}
