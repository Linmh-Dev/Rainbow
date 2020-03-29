package com.rainbow.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wordtable")
public class Word {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "english")
    String english;
    @ColumnInfo(name = "chinaese")
    String chinaese;

    public Word(String english, String chinaese) {
        this.english = english;
        this.chinaese = chinaese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinaese() {
        return chinaese;
    }

    public void setChinaese(String chinaese) {
        this.chinaese = chinaese;
    }
}
