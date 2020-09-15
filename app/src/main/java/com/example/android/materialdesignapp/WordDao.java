package com.example.android.materialdesignapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY title ASC")
    LiveData<List<Word>> getWord();

    @Query("SELECT * FROM word_table WHERE id=:id")
     LiveData<Word>getNote(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table WHERE id IN (:id)")
    void deleteRow(int id);

    @Query("UPDATE word_table SET title=:title,description=:desc WHERE id=:id")
    void updateRow(int id,String title,String desc);

}
