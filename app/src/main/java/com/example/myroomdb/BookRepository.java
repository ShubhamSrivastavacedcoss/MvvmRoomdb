package com.example.myroomdb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myroomdb.Dao.BookDao;
import com.example.myroomdb.Entity.BookEntity;

import java.util.List;

public class BookRepository {

    private BookDao bookDao;
    LiveData<List<BookEntity>> getBooks;

    public BookRepository(Application application) {

        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        getBooks = bookDao.getAllBooks();
    }

    public void insert(BookEntity bookEntity) {
        new InsertBookAsynTask(bookDao).execute(bookEntity);

    }

    public void update(BookEntity bookEntity) {
        new UpdateBookAsynTask(bookDao).execute(bookEntity);
    }

    public void delete(BookEntity bookEntity) {
        new DeleteBookAsynTask(bookDao).execute(bookEntity);
    }

    public void deleteAll(){
        new DeleteAllBookAsynTask(bookDao).execute();

    }

    public LiveData<List<BookEntity>> getGetBooks() {
        return getBooks;
    }

    public static class InsertBookAsynTask extends AsyncTask<BookEntity,Void,Void>{

        BookDao bookDao;

        public InsertBookAsynTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.insert(bookEntities[0]);
            return null;
        }
    }

    public static class UpdateBookAsynTask extends AsyncTask<BookEntity,Void,Void>{

        BookDao bookDao;

        public UpdateBookAsynTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.update(bookEntities[0]);
            return null;
        }
    }

    public class DeleteBookAsynTask extends AsyncTask<BookEntity,Void,Void>{

        BookDao bookDao;

        public DeleteBookAsynTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.delete(bookEntities[0]);
            return null;
        }
    }

    public class DeleteAllBookAsynTask extends AsyncTask<BookEntity,Void,Void>{

        BookDao bookDao;

        public DeleteAllBookAsynTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.deleteAll();
            return null;
        }
    }
}
