package com.example.mybookactivity.ui.bookdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.ActivityBookDetailsBinding;
import com.example.mybookactivity.logic.Repository;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.dao.entity.Book;
import com.example.mybookactivity.logic.dao.entity.BookShelf;
import com.example.mybookactivity.logic.model.BookSearch;
import com.example.mybookactivity.ui.book.RefreshBookShelfEvent;
import com.example.mybookactivity.ui.chapter.ChapterActivity;
import com.example.mybookactivity.ui.showbookcontent.ShowBookContentActivity;
import com.google.android.material.appbar.AppBarLayout;

import org.greenrobot.eventbus.EventBus;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class BookDetailsActivity extends AppCompatActivity {

    private ActivityBookDetailsBinding binding;
    BookSearch.DataBean dataBean;
    Book book;
    BookShelf bookShelf;
    private int id2;
    private int id3;
    private int isHas;
    private String url;
    private String bookAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.myDetailsToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(false);

        initData();

        binding.myDetailsAppBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                    binding.myCollapsingToolbarLayout.setTitle("");
                    //展开状态

                }else if(state == State.COLLAPSED){
                    getWindow().setStatusBarColor(getColor(R.color.maincolor));
                    binding.myCollapsingToolbarLayout.setTitle("书籍详情");
                    //折叠状态

                }else {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                    binding.myCollapsingToolbarLayout.setTitle("");
                    //中间状态
                }
            }
        });
        binding.bookDetailsChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterActivity.actionStartChapterActivity(BookDetailsActivity.this,id2,url,bookAuthor);
            }
        });
        binding.bookDetailsReadNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBookContentActivity.actionStartBookContentActivity(BookDetailsActivity.this,id2,id3,url,bookAuthor);
            }
        });
        binding.bookDetailsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetailsActivity.this,"谢谢你的好评！",Toast.LENGTH_SHORT).show();
            }
        });

        isHas = BookDao.isHasBookShelf(id2);
        if(isHas==1){
            binding.bookDetailsAdd.setText("移出书架");
        }
        else {
            binding.bookDetailsAdd.setText("加入书架");
        }
        binding.bookDetailsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHas==1){
                    binding.bookDetailsAdd.setText("加入书架");
                    BookDao.deleteBookShelf(id2);
                    Toast.makeText(BookDetailsActivity.this,"移出书架成功",Toast.LENGTH_SHORT).show();
                    isHas=0;
                }
                else {
                    binding.bookDetailsAdd.setText("移出书架");
                    boolean result = Repository.getInstance().insertIntoBookShelf(id2);
                    Toast.makeText(BookDetailsActivity.this,"加入书架成功",Toast.LENGTH_SHORT).show();
                    isHas=1;
                }
                EventBus.getDefault().post(new RefreshBookShelfEvent());
            }
        });
    }


    private void initData() {
         dataBean = (BookSearch.DataBean) getIntent().getSerializableExtra("dataBean");
        if(dataBean!=null){
            binding.bookTitle.setText(dataBean.getName());
            Glide.with(this).load(dataBean.getImg()).into(binding.mytoolbarImg);
            Glide.with(this)
                    .load(dataBean.getImg())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                    .into(binding.detailsBg);
            binding.detailsDesc.setText(dataBean.getDesc());
            binding.detailsAuthor.setText(dataBean.getAuthor());
            binding.detailsLastChapter.setText(dataBean.getLastChapter());
            binding.detailsStatus.setText(dataBean.getBookStatus());
            id2 = Integer.parseInt(dataBean.getId());
            id3 = Integer.parseInt(dataBean.getLastChapterId());
            url=dataBean.getImg();
            bookAuthor=dataBean.getAuthor();

        }
         book=(Book)getIntent().getSerializableExtra("book");
        if(book!=null){
            binding.bookTitle.setText(book.getBookName());
            Glide.with(this).load(MyApplicaton.url+book.getBookPic()).into(binding.mytoolbarImg);
            Glide.with(this)
                    .load(MyApplicaton.url+book.getBookPic())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                    .into(binding.detailsBg);
            binding.detailsDesc.setText(book.getDesc());
            binding.detailsAuthor.setText(book.getAuthor());
            binding.detailsLastChapter.setText(book.getLastTime());
            binding.detailsStatus.setText(book.getBookStatus());
            id2=book.getId2();
            id3=book.getFirstChapterId();
            url=MyApplicaton.url+book.getBookPic();
            bookAuthor=book.getAuthor();
        }
        bookShelf = (BookShelf)getIntent().getSerializableExtra("bookShelf");
        if(bookShelf!=null){
            binding.bookTitle.setText(bookShelf.getBookName());
            Glide.with(this).load(MyApplicaton.url+bookShelf.getBookPic()).into(binding.mytoolbarImg);
            Glide.with(this)
                    .load(MyApplicaton.url+bookShelf.getBookPic())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                    .into(binding.detailsBg);
            binding.detailsDesc.setText(bookShelf.getDesc());
            binding.detailsAuthor.setText(bookShelf.getAuthor());
            binding.detailsLastChapter.setText(bookShelf.getLastChapter());
            binding.detailsStatus.setText(bookShelf.getBookStatus());
            id2=bookShelf.getId2();
            id3=bookShelf.getFirstChapterId();
            url=MyApplicaton.url+bookShelf.getBookPic();
            bookAuthor=bookShelf.getAuthor();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
    public static void actionBookStart(Context context, Book book){
        Intent intent = new Intent(context,BookDetailsActivity.class);
        intent.putExtra("book",book);
        context.startActivity(intent);
    }
    public static void actionSearchStart(Context context, BookSearch.DataBean dataBean){
        Intent intent = new Intent(context,BookDetailsActivity.class);
        intent.putExtra("dataBean",dataBean);
        context.startActivity(intent);
    }
    public static void actionShelfStart(Context context, BookShelf bookShelf){
        Intent intent = new Intent(context,BookDetailsActivity.class);
        intent.putExtra("bookShelf",bookShelf);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}