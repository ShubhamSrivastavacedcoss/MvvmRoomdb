package com.example.myroomdb.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myroomdb.Entity.BookEntity;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(BookEntity bookEntity);

    @Update
    void update(BookEntity bookEntity);

    @Delete
    void delete(BookEntity bookEntity);

    @Query("DELETE FROM book_table")
    void deleteAll();

    @Query("SELECT * FROM book_table")
    LiveData<List<BookEntity>> getAllBooks();
}
