package com.rainbow.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rainbow.room.Word;

import java.util.List;

public class WordGson {
    String s;

    public WordGson(String s) {
        this.s = s;
    }

    public List<WordEntity> getEntity(){
        Gson gson=new Gson();
        List<WordEntity> list=gson.fromJson("["+s+"]",new TypeToken<List<WordEntity>>(){}.getType());
        return list;
    }

    public String getResult() {
        List<WordEntity> entity = getEntity();
        String ssr="";
        for (WordEntity ss:entity
        ) {
            ssr+=ss.getTgt();
        }
        return ssr;

    }
    public String getQuery(){
        List<WordEntity> entity = getEntity();
        String ssr="";
        for (WordEntity ss:entity
        ) {
            ssr+=ss.getSrc();
        }
        return ssr;
    }


}
