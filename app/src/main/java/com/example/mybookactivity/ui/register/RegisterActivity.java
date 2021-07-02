package com.example.mybookactivity.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {


    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //floatingbar图标设置为原来的颜色
        binding.registerFloatingBar.setImageTintList(null);

        binding.registerFloatingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("mememe", "onClick: "+binding.registerAccount.getText().toString().trim()+binding.registerAccountPwd.getText().toString());
                if(binding.registerAccount.getText().toString().trim()!="" && binding.registerAccountPwd.getText().toString().equals(binding.registerAccountPwdOk.getText().toString())){
                    Intent intent = new Intent();
                    intent.putExtra("number",Integer.parseInt(binding.registerAccount.getText().toString()));
                    intent.putExtra("pwd",binding.registerAccountPwd.getText().toString());
                    setResult(RESULT_OK,intent);
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"请正确输入",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ActivityCollector.getInstance().removeActivity(this);
//    }
}