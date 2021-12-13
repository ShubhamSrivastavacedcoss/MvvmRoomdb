package com.example.myroomdb.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bookName;
    private String author;

    public BookEntity(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }
}
