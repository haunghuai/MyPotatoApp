package com.example.mybookactivity.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.MefragmentBinding;
import com.example.mybookactivity.databinding.NavHeadBinding;
import com.example.mybookactivity.ui.login.LoginActivity;
import com.example.mybookactivity.ui.mecenter.MeCenterActivity;
import com.google.android.material.navigation.NavigationView;

public class MeFragment extends Fragment {

    private MefragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MefragmentBinding.inflate(inflater,container,false);

        NavHeadBinding head = NavHeadBinding.inflate(getLayoutInflater(),binding.meNav,true);


        head.myheadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MeCenterActivity.class);
                getContext().startActivity(intent);
            }
        });

        binding.meNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch ((item.getItemId())){
                    case R.id.check:
                        Toast.makeText(getContext(),"这已经是最新版本啦",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.goodComment:
                        Toast.makeText(getContext(),"加作者好友:hh139264555微信给个好评哦！",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.aboutMe:
                        Toast.makeText(getContext(),"作者：黄淮",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exitLogin:
                        ActivityCollector.getInstance().fishAll();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });


        return binding.getRoot();
    }
}
