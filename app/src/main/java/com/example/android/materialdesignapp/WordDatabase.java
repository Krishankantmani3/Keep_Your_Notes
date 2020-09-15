package com.example.android.materialdesignapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
 abstract class WordDatabase extends RoomDatabase {
    abstract  WordDao wordDao();
   private static volatile WordDatabase INSTANCE;

    static WordDatabase getWordDatabase(final Context context){
        if(INSTANCE==null) {
            synchronized (WordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, "word_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }


}
