package com.example.mybookactivity.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.mybookactivity.databinding.ActivityWelcomeBinding;
import com.example.mybookactivity.ui.MainActivity;
import com.example.mybookactivity.ui.login.LoginActivity;

/**
 * @author huanghuai
 *
 */
public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bgtitle.animate().translationY(-2400).setDuration(1200).setStartDelay(3000);
        binding.bgmain.animate().translationY(-2400).setDuration(1200).setStartDelay(3000);
        binding.myAnimation.animate().translationY(1600).setDuration(1200).setStartDelay(3000);

        handler.sendEmptyMessageDelayed(0,4*1000);
    }
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);    //收到消息后跳转
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    };
}