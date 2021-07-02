package com.example.mybookactivity.ui.complete;

import android.content.Context;
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
import com.example.mybookactivity.ui.search.SearchRecyclerViewAdapter;

import java.util.List;

public class CompleteBookAdapter extends RecyclerView.Adapter<CompleteBookAdapter.ViewHolder> {

    private Context mContext;
    private List<Book> mList;

    public CompleteBookAdapter(Context context, List<Book> list){
        this.mContext = context;
        this.mList = list;

    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView bookName,cName,author,desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cBookPic);
            bookName = itemView.findViewById(R.id.cBookName);
            cName = itemView.findViewById(R.id.cCname);
            author =itemView.findViewById(R.id.cAuthor);
            desc = itemView.findViewById(R.id.cDesc);
            view = itemView;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complete_book_iten,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = mList.get(position);
        holder.author.setText(book.getAuthor());
        holder.cName.setText(book.getcName());
        holder.desc.setText(book.getDesc());
        holder.bookName.setText(book.getBookName());
        Glide.with(mContext).load(MyApplicaton.url+book.getBookPic()).into(holder.imageView);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionBookStart(mContext,book);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
