package com.example.myroomdb.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myroomdb.BookRepository;
import com.example.myroomdb.Entity.BookEntity;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    BookRepository bookRepository;
    private LiveData<List<BookEntity>> getBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
        getBooks = bookRepository.getGetBooks();
    }

    public void insert(BookEntity bookEntity){
        bookRepository.insert(bookEntity);
    }

    public void update(BookEntity bookEntity){
        bookRepository.update(bookEntity);
    }

    public void delete(BookEntity bookEntity){
        bookRepository.delete(bookEntity);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }

    public LiveData<List<BookEntity>> getGetBooks() {
        return getBooks;
    }
}
