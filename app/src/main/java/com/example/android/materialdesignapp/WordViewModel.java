package com.example.android.materialdesignapp;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<List<Word>> mWords;

    public WordViewModel( Application application) {
        super(application);
        mWordRepository=new WordRepository(application);
        mWords=mWordRepository.getAllWords();
    }

    void insert(Word word){mWordRepository.insert(word);}

    void update(int id,String title,String desc){ mWordRepository.updateRow(id,title,desc);}

    void delete(int id){mWordRepository.deleteRow(id);}

    LiveData<List<Word>> getAllwords(){return mWords;}

    LiveData<Word> getNote(int id){return mWordRepository.getNote(id);}


}


