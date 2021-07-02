package com.example.mybookactivity.ui.showbookcontent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.ActivityShowBookBinding;
import com.example.mybookactivity.logic.model.BookPassage;

public class ShowBookContentActivity extends AppCompatActivity {


    private ActivityShowBookBinding binding;
    private ShowContentViewModel viewModel;
    private BookPassage currentBookPassage;

    private ShowBookContentDialog dialog;

    private int lastX=0;
    private int lastY=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityShowBookBinding.inflate(getLayoutInflater());
        viewModel = ViewModelProviders.of(this).get(ShowContentViewModel.class);
        setContentView(binding.getRoot());

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        int id2 = getIntent().getIntExtra("id2",608945);
        int id3 = getIntent().getIntExtra("id3",3101682);
        String url = getIntent().getStringExtra("bookUrl");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");

        viewModel.refreshContent(id2,id3);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("正在加载小说...");
        binding.showContentScrollView.setVisibility(View.GONE);
        progressDialog.show();

        viewModel.bookPassageLiveData.observe(this, new Observer<BookPassage>() {
            @Override
            public void onChanged(BookPassage bookPassage) {
                binding.showContentScrollView.setVisibility(View.VISIBLE);
                currentBookPassage = bookPassage;
                binding.showBookName.setText(bookPassage.getData().getCname());
                binding.showBookContent.setText(bookPassage.getData().getContent());
                dialog = new ShowBookContentDialog(ShowBookContentActivity.this,R.style.DialogTheme,bookPassage,id2,binding.showBookContentView,url,bookAuthor,binding.showBookContent);
                progressDialog.dismiss();
            }
        });
        binding.toLastChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = currentBookPassage.getData().getPid();
                if (pid!=-1){
                    ShowBookContentActivity.actionStartBookContentActivity(ShowBookContentActivity.this,id2,pid,url,bookAuthor);
                    finish();
                }
                else {
                    Toast.makeText(ShowBookContentActivity.this,"已经没有上一章了！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.toNextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nid=currentBookPassage.getData().getNid();
                if(nid!=-1){
                    ShowBookContentActivity.actionStartBookContentActivity(ShowBookContentActivity.this,id2,nid,url,bookAuthor);
                    finish();
                }
                else {
                    Toast.makeText(ShowBookContentActivity.this,"已经没有下一章了！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.showContentScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        if(Math.abs(x-lastX) < 5 || Math.abs(y-lastY) < 5){
                            Window window = dialog.getWindow();
                            window.setGravity(Gravity.BOTTOM);
                            window.setWindowAnimations(R.style.dialog_menu_animStyle);
                            //设置对话框大小
                            window.getDecorView().setPadding(0,0,0,0);
                            WindowManager.LayoutParams layoutParams=window.getAttributes();
                            //设置宽度和高度
                            layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
                            layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
                            //显示Dialog
                            dialog.show();
                        }
                        break;


                }

                return false;
            }
        });

    }

    public static void actionStartBookContentActivity(Context context,int id2, int id3,String url,String bookAuthor){
        Intent intent = new Intent(context,ShowBookContentActivity.class);
        intent.putExtra("id2",id2);
        intent.putExtra("id3",id3);
        intent.putExtra("bookUrl",url);
        intent.putExtra("bookAuthor",bookAuthor);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}