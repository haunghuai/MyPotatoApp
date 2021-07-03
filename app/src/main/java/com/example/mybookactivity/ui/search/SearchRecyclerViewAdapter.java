package com.example.mybookactivity.ui.search;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.ui.book.RefreshBookShelfEvent;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {

    public Context mContext;
    public List<BookSearch.DataBean> mBookSearchList;
    private ProgressDialog progressDialog;
    public SearchRecyclerViewAdapter(Context context,List<BookSearch.DataBean> bookSearchList){
        this.mContext = context;
        this.mBookSearchList = bookSearchList;
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView,addBook;
        TextView bookName,cName,author,desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.searchBookPic);
            bookName = itemView.findViewById(R.id.searchBookName);
            cName = itemView.findViewById(R.id.searchCname);
            author =itemView.findViewById(R.id.searchAuthor);
            desc = itemView.findViewById(R.id.searchDesc);
            addBook = itemView.findViewById(R.id.searchAddBook);
            view = itemView;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_book_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookSearch.DataBean dataBean = mBookSearchList.get(position);
        holder.author.setText(dataBean.getAuthor());
        holder.cName.setText(dataBean.getCName());
        holder.desc.setText(dataBean.getDesc());
        holder.bookName.setText(dataBean.getName());
        Glide.with(mContext).load(dataBean.getImg()).into(holder.imageView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDetailsActivity.actionSearchStart(mContext,dataBean);
            }
        });
        holder.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                int id2 =  Integer.parseInt(mBookSearchList.get(position).getId());
                int result1 = BookDao.isHasBookShelf(id2);
                if(result1 == 0){
                    boolean result = Repository.getInstance().insertIntoBookShelf(id2);
                    if(result){
                        Toast.makeText(mContext,"加入书架成功",Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new RefreshBookShelfEvent());
                    }
                    else {Toast.makeText(mContext,"加入书架失败",Toast.LENGTH_SHORT).show();}
                }
                else {
                    Toast.makeText(mContext,"书架已经有该本书啦！",Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mBookSearchList!=null){
            return mBookSearchList.size();
        }
        return 0;
    }

}
