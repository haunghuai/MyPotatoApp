package com.example.mybookactivity.ui.chapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybookactivity.R;
import com.example.mybookactivity.logic.model.BookIndex;
import com.example.mybookactivity.ui.showbookcontent.ShowBookContentActivity;

import java.util.List;

public class ChapterRecyclerViewAdapter extends RecyclerView.Adapter<ChapterRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<BookIndex.DataBean.ListBeanX.ListBean> mListBeans;
    private int id;
    private String bookUrl;
    private String bookAuthor;
    public ChapterRecyclerViewAdapter(Context context,List<BookIndex.DataBean.ListBeanX.ListBean> listBeans,int id,String bookUrl,String bookAuthor){
        this.mContext = context;
        this.mListBeans = listBeans;
        this.id=id;
        this.bookUrl = bookUrl;
        this.bookAuthor=bookAuthor;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView chapterName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            chapterName = itemView.findViewById(R.id.chapterItemName);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.chapter_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookIndex.DataBean.ListBeanX.ListBean listBean = mListBeans.get(viewHolder.getAdapterPosition());
                ShowBookContentActivity.actionStartBookContentActivity(mContext,id,listBean.getId(),bookUrl,bookAuthor);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookIndex.DataBean.ListBeanX.ListBean listBean = mListBeans.get(position);
        holder.chapterName.setText(listBean.getName());

    }

    @Override
    public int getItemCount() {
        return mListBeans.size();
    }


}
