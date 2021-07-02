package com.example.mybookactivity.ui.book;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.ui.bookdetails.BookDetailsActivity;

import java.util.List;


public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.ViewHolder> {
    Context mContext;
    List<BookShelf> mBookShelfList;
    public MyBookDetailsDialog myBookDetailsDialog;
    private AlertDialog alertDialog;

    public MyBookAdapter(Context context, List<BookShelf> list){
        this.mContext = context;
        this.mBookShelfList = list;

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private View layout;
        private ImageView bookDetails;
        private ImageView imagePic;
        private TextView author,bookName,status,lastChapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            bookDetails = itemView.findViewById(R.id.bookDetails);
            imagePic = itemView.findViewById(R.id.bookShelfPic);
            author = itemView.findViewById(R.id.bookShelfBookAuthor);
            bookName = itemView.findViewById(R.id.bookShelfBookName);
            status = itemView.findViewById(R.id.bookShelfStatus);
            lastChapter = itemView.findViewById(R.id.bookShelfLastChapter);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_bookitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookShelf bookShelf = mBookShelfList.get(viewHolder.getAdapterPosition());
                BookDetailsActivity.actionShelfStart(mContext,bookShelf);
            }
        });

        viewHolder.bookDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookShelf bookShelf = mBookShelfList.get(viewHolder.getAdapterPosition());
                myBookDetailsDialog = new MyBookDetailsDialog(mContext,R.style.DialogTheme,bookShelf);
                Window window = myBookDetailsDialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.dialog_menu_animStyle);
                //设置对话框大小
                window.getDecorView().setPadding(0,0,0,0);
                WindowManager.LayoutParams layoutParams=window.getAttributes();
                //设置宽度和高度
                layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
                //显示Dialog
                myBookDetailsDialog.show();
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog = new AlertDialog.Builder(mContext)
                                .setTitle("提示:")
                                .setMessage("确认删除本书吗？")
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        BookShelf bookShelf = mBookShelfList.get(viewHolder.getAdapterPosition());
                                        BookDao.deleteBookShelf(bookShelf.getId2());
                                        notifyDataSetChanged();
                                    }
                                })
                                .create();
                                alertDialog.show();
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookShelf bookShelf = mBookShelfList.get(position);
        Glide.with(mContext).load(MyApplicaton.url+bookShelf.getBookPic()).into(holder.imagePic);
        holder.author.setText(bookShelf.getAuthor());
        holder.bookName.setText(bookShelf.getBookName());
        holder.status.setText(bookShelf.getBookStatus());
        holder.lastChapter.setText(bookShelf.getLastChapter());

    }

    @Override
    public int getItemCount() {
        return mBookShelfList.size();
    }





}
