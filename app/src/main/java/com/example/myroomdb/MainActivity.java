package com.example.myroomdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myroomdb.Adapter.BookAdapter;
import com.example.myroomdb.Entity.BookEntity;
import com.example.myroomdb.ViewModel.BookViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BookViewModel bookViewModel;
    RecyclerView booksRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksRecyclerView = findViewById(R.id.recycler_view_book);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);

        final BookAdapter bookAdapter = new BookAdapter();
        booksRecyclerView.setAdapter(bookAdapter);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getGetBooks().observe(this, new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(List<BookEntity> bookEntities) {

                bookAdapter.setBookEntities(bookEntities);
                Toast.makeText(MainActivity.this, "yessss", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT
        | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                bookViewModel.delete(bookAdapter.getBookEntity(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Book deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(booksRecyclerView);

        bookAdapter.seletedOnItemSeletedListner(new BookAdapter.OnItemSeletedListner() {
            @Override
            public void onItemClick(BookEntity bookEntity) {
                Intent intent = new Intent(MainActivity.this,AddEditBookActivity.class);
                intent.putExtra("BOOKID",bookEntity.getId());
                intent.putExtra("BOOKNAME",bookEntity.getBookName());
                intent.putExtra("AUTHORNAME",bookEntity.getAuthor());

                startActivityForResult(intent,22);

            }
        });
    }

    public void addEditBookBtn(View view) {

        Intent intent = new Intent(this, AddEditBookActivity.class);
        startActivityForResult(intent, 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11 && resultCode == RESULT_OK) {

            String bookName = data.getStringExtra("BOOKNAME");
            String authorName = data.getStringExtra("AUTHORNAME");


            BookEntity bookEntity = new BookEntity(bookName,authorName);
            bookViewModel.insert(bookEntity);
            Toast.makeText(this, "Book saved", Toast.LENGTH_SHORT).show();

        }else if (requestCode == 22 && resultCode == RESULT_OK){
            int id = data.getIntExtra("BOOKID",-1);
            if (id == -1){
                Toast.makeText(this, "Books can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String bookName = data.getStringExtra("BOOKNAME");
            String authorName = data.getStringExtra("AUTHORNAME");

            BookEntity bookEntity = new BookEntity(bookName,authorName);
            bookEntity.setId(id);
            bookViewModel.update(bookEntity);
            Toast.makeText(this, "Books Updated", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Book not saved", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deleteall_item_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteall_button:
                bookViewModel.deleteAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}