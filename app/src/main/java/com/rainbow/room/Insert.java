package com.rainbow.room;

import android.os.AsyncTask;

public class Insert extends AsyncTask<Word,Void,Void> {
    @Override
    protected Void doInBackground(Word... words) {
        WordDao wordDao=WordDatabases.getWordDatabases().getDao();
        for (Word w:words
             ) {
            wordDao.insert(w);
        }
        return null;
    }
}
