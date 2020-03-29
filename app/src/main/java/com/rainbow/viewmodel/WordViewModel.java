package com.rainbow.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rainbow.room.Word;

public class WordViewModel extends ViewModel {
   private static MutableLiveData<Word> wordMutableLiveData;

    public MutableLiveData<Word> getWordMutableLiveData() {
        if (wordMutableLiveData==null){
            wordMutableLiveData=new MutableLiveData<>();
        }
        return wordMutableLiveData;
    }

    public static void setWordMutableLiveData(Word word) {
        if (wordMutableLiveData==null){
            wordMutableLiveData=new MutableLiveData<>();
        }
      wordMutableLiveData.setValue(word);
    }
}
