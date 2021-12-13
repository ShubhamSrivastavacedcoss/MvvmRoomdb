package com.example.myroomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myroomdb.Dao.BookDao;
import com.example.myroomdb.Entity.BookEntity;

@Database(entities = BookEntity.class,version = 1)
public abstract class BookDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    private static BookDatabase instance;

    public static synchronized BookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, BookDatabase.class, "book_database"
            ).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
