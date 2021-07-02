package com.example.mybookactivity.ui.recommand;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;

import java.util.List;

public class HotRefreshAdapter extends RecyclerView.Adapter<HotRefreshAdapter.ViewHolder> {
    private Context mContext;
    private List<Book> mBookList;
    public HotRefreshAdapter(Context mContext, List<Book> bookList){
        this.mContext = mContext;
        this.mBookList = bookList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
         ImageView imageView;
         TextView bookName,bookAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.hotItemPic);
            bookName = itemView.findViewById(R.id.hotItemBookName);
            bookAuthor = itemView.findViewById(R.id.hotItemAuthor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hotrefresh_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = mBookList.get(viewHolder.getAdapterPosition());
                BookDetailsActivity.actionBookStart(mContext,book);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Book book = mBookList.get(position);
            holder.bookAuthor.setText(book.getAuthor());
            holder.bookName.setText(book.getBookName());
            Glide.with(mContext).load(MyApplicaton.url+book.getBookPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }


}
