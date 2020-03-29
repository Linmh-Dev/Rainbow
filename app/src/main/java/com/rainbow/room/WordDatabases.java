package com.rainbow.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rainbow.MyApplication;

@Database(entities = Word.class,version = 1,exportSchema = false)
public abstract class WordDatabases extends RoomDatabase {
    static WordDatabases wordDatabases;

    public static WordDatabases getWordDatabases() {
        if (wordDatabases==null){
            wordDatabases= Room.databaseBuilder(MyApplication.getContext().getApplicationContext(),WordDatabases.class,"WordData").build();
        }
        return wordDatabases;
    }
    public abstract WordDao getDao();
}
