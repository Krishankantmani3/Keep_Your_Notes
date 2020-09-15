package com.example.android.materialdesignapp;

import android.app.Application;
import java.util.List;
import androidx.lifecycle.LiveData;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        WordDatabase db=WordDatabase.getWordDatabase(application);
        this.mWordDao = (WordDao)db.wordDao();
        this.allWords = mWordDao.getWord();
    }

    LiveData<List<Word>> getAllWords()
    {
        return allWords;
    }

    void insert(final Word word){
       new Thread(new Runnable() {
           @Override
           public void run() {
               mWordDao.insert(word);
           }
       }).start();
    }


    void deleteRow(final int id)
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               mWordDao.deleteRow(id);
           }
       }).start();
    }


    void updateRow(final int id, final String title, final String desc){
        new Thread(new Runnable() {
            @Override
            public void run() {
               mWordDao.updateRow(id,title,desc);
            }
        }).start();
    }

    LiveData<Word> getNote(int id){return  mWordDao.getNote(id);}


}
