package com.example.mybookactivity.ui.mecenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.databinding.ActivityMeCenterBinding;
import com.example.mybookactivity.ui.login.LoginActivity;

public class MeCenterActivity extends AppCompatActivity {

    private ActivityMeCenterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.getInstance().addActivity(this);

        binding = ActivityMeCenterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        binding.meCenterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.meCenterExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MeCenterActivity.this, LoginActivity.class);
                startActivity(intent);
                ActivityCollector.getInstance().fishAll();

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}