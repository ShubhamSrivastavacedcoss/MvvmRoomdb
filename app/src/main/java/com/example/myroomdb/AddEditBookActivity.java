package com.example.myroomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AddEditBookActivity extends AppCompatActivity {

    AppCompatEditText bookNameEt, authorNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);

        bookNameEt = findViewById(R.id.bookname_et);
        authorNameEt = findViewById(R.id.author_et);
        //setTitle("Add Note");

        Intent intent = getIntent();
        if (intent.hasExtra("BOOKID")){
            setTitle("Edit Note");
            bookNameEt.setText(intent.getStringExtra("BOOKNAME"));
            authorNameEt.setText(intent.getStringExtra("AUTHORNAME"));
        }else {
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_item_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_btn:
                saveData();
              //  Toast.makeText(AddEditBookActivity.this, "saved", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveData() {

        String bookName = bookNameEt.getText().toString();
        String authorName = authorNameEt.getText().toString();

        if (bookName.trim().isEmpty() || authorName.trim().isEmpty()){
            Toast.makeText(this, "Please enter the book name and the author", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent();
        intent.putExtra("BOOKNAME",bookName);
        intent.putExtra("AUTHORNAME",authorName);


        int id = getIntent().getIntExtra("BOOKID",-1);
        if (id!=-1){
            intent.putExtra("BOOKID",id);
        }

        setResult(RESULT_OK,intent);
        finish();
    }
}