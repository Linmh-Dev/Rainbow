package com.rainbow.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {
    @Insert
    void insert(Word...words);
    @Query("delete from wordtable")
    void delete();
    @Query("select * from wordtable order by id desc")
    DataSource.Factory<Integer,Word> getList();
}
