package com.example.myroomdb.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomdb.Entity.BookEntity;
import com.example.myroomdb.R;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    List<BookEntity> bookEntities = new ArrayList<>();
    OnItemSeletedListner listner;


    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_item, parent, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        BookEntity currentBook = bookEntities.get(position);
        holder.bookTv.setText(currentBook.getBookName());
        holder.authorTv.setText(currentBook.getAuthor());

    }

    @Override
    public int getItemCount() {
        return bookEntities.size();
    }

    public void setBookEntities(List<BookEntity> bookEntityList) {
        this.bookEntities = bookEntityList;
        notifyDataSetChanged();
    }

    public BookEntity getBookEntity(int position) {
        return bookEntities.get(position);
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        TextView bookTv, authorTv;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bookTv = itemView.findViewById(R.id.bookname_tv);
            authorTv = itemView.findViewById(R.id.author_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();

                    if (listner != null && postion != RecyclerView.NO_POSITION) {
                        listner.onItemClick(bookEntities.get(postion));
                    }
                }
            });
        }


    }

    public interface OnItemSeletedListner {
        void onItemClick(BookEntity bookEntity);
    }

    public void seletedOnItemSeletedListner(OnItemSeletedListner onItemSeletedListner) {
        this.listner = onItemSeletedListner;

    }
}
