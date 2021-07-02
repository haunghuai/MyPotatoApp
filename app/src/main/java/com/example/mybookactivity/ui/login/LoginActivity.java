package com.example.mybookactivity.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivityLoginBinding;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.ui.MainActivity;
import com.example.mybookactivity.ui.register.RegisterActivity;
import com.example.mybookactivity.utils.GetBookIdUtil;

import org.litepal.LitePal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private int registNumber;
    private String registPwd;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.getDatabase();
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //floatingbar图标设置为原来的颜色
        binding.loginFloatingBar.setImageTintList(null);


        SharedPreferences sharedPreferences1 = getSharedPreferences("firstCreateApp",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        boolean first = sharedPreferences1.getBoolean("isFirst",true);

        if(first){
            editor1.putBoolean("isFirst",false);
            editor1.apply();
            ProgressDialog progressDialog1 = new ProgressDialog(this);
            progressDialog1.setTitle("第一次初始化应用较慢，请耐心等待。。。");
            progressDialog1.setCanceledOnTouchOutside(false);

            int[] ids = new int[]{
                    608945,248872,427443,122085,625913,626638,600715,445578,612233,253077,521014,626900,489725,597558,17779,
                    624721,412435,480889,612821,626204,5397,436,524367,286490,95176,515258,176504

            };
            BookInfoServiceCreator serviceCreator = BookInfoServiceCreator.getInstance();
            BookInfoService bookInfoService = serviceCreator.create(BookInfoService.class);
            for(int i=0;i<ids.length;i++){
                int id = ids[i];
                progressDialog1.show();
                bookInfoService.getBookInfo(GetBookIdUtil.getBookId(id),id).enqueue(new Callback<BookInfo>() {
                    @Override
                    public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                        BookInfo bookInfo = response.body();
                        if(bookInfo!=null && bookInfo.getInfo().equals("success")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean result =  BookDao.insertBook(bookInfo);

                                }
                            }).start();

                        }
                        else {
                            Toast.makeText(LoginActivity.this,"查无此数据",Toast.LENGTH_SHORT).show();

                        }
                        progressDialog1.dismiss();

                    }

                    @Override
                    public void onFailure(Call<BookInfo> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }







        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
         editor =sharedPreferences.edit();


        binding.loginToRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        String number = sharedPreferences.getString("number","-1");
        String pwd = sharedPreferences.getString("pwd","-1");
        if(number != "-1" && pwd!="-1"){
            binding.myAccount.setText(number);
            binding.myAccountPwd.setText(pwd);
            binding.checkBox.setChecked(true);

        }
        binding.loginFloatingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.checkBox.isChecked()){
                    editor.clear();
                    editor.putString("number",binding.myAccount.getText().toString());
                    editor.putString("pwd",binding.myAccountPwd.getText().toString());
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    editor.clear();
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("mememe", "onResume: "+registNumber+" "+registPwd);
        if(registNumber!=-1&&registPwd!=null){
            binding.myAccount.setText(String.valueOf(registNumber));
            binding.myAccountPwd.setText(registPwd);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    registNumber = data.getIntExtra("number",-1);
                    registPwd = data.getStringExtra("pwd");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}