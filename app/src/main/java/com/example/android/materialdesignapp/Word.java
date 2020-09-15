package com.example.android.materialdesignapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name ="title")
    @NonNull
    private
    String title ;

    @ColumnInfo(name="description")
    @NonNull
    private
    String description;

    Word(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

     @NonNull
    public int getId() {
        return id;
    }
}
