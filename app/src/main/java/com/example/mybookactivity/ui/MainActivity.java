package com.example.mybookactivity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mybookactivity.ActivityCollector;
import com.example.mybookactivity.MyApplicaton;
import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.ActivityMainBinding;
import com.example.mybookactivity.logic.dao.BookDao;
import com.example.mybookactivity.logic.model.BookInfo;
import com.example.mybookactivity.logic.network.BookInfoService;
import com.example.mybookactivity.logic.network.BookInfoServiceCreator;
import com.example.mybookactivity.ui.book.BookFragment;
import com.example.mybookactivity.ui.me.MeFragment;
import com.example.mybookactivity.ui.recommand.RecommandFragment;
import com.example.mybookactivity.ui.search.SearchActivity;
import com.example.mybookactivity.utils.GetBookIdUtil;

import org.litepal.LitePal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    Fragment bookfragment,mefragment;
    Fragment recommandfragment;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.getDatabase();
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        init();

        ActivityCollector.getInstance().addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }

    public void init(){
        bookfragment = new BookFragment();
        mefragment = new MeFragment();
        recommandfragment = new RecommandFragment();

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_contain,bookfragment);
        ft.add(R.id.main_contain,recommandfragment);
        ft.add(R.id.main_contain,mefragment);

        ft.show(bookfragment).hide(recommandfragment).hide(mefragment);
        ft.commit();

        mainBinding.btnBook.setOnClickListener(v->{replaceFragment(bookfragment);});
        mainBinding.btnShop.setOnClickListener(v->{replaceFragment(recommandfragment);});
        mainBinding.btnMe.setOnClickListener(v->{replaceFragment(mefragment);});
        mainBinding.navSearch.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }
    public void replaceFragment(Fragment fragment){
        changeIcon(fragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(bookfragment).hide(recommandfragment).hide(mefragment);
        ft.show(fragment);
        ft.commit();

    }
    public void changeIcon(Fragment fragment){
        mainBinding.imgBook.setImageResource(R.drawable.book_n);
        mainBinding.imgShop.setImageResource(R.drawable.bookshop_n);
        mainBinding.imgMe.setImageResource(R.drawable.me_n);
        mainBinding.tvBook.setTextColor((Color.parseColor("#c3c3c3")));
        mainBinding.tvShop.setTextColor((Color.parseColor("#c3c3c3")));
        mainBinding.tvMe.setTextColor((Color.parseColor("#c3c3c3")));
        if(fragment instanceof BookFragment){
            mainBinding.imgBook.setImageResource(R.drawable.book_h);
            mainBinding.tvBook.setTextColor((Color.parseColor("#01abf9")));
            mainBinding.navText.setText("书架");
            mainBinding.mytoolbar.setVisibility(View.VISIBLE);

        }
        else if(fragment instanceof RecommandFragment){
            mainBinding.imgShop.setImageResource(R.drawable.bookshop_h);
            mainBinding.tvShop.setTextColor((Color.parseColor("#01abf9")));
            mainBinding.mytoolbar.setVisibility(View.VISIBLE);
            mainBinding.navText.setText("土豆小说");

        }
        else if(fragment instanceof MeFragment){
            mainBinding.imgMe.setImageResource(R.drawable.me_h);
            mainBinding.tvMe.setTextColor((Color.parseColor("#01abf9")));
            mainBinding.mytoolbar.setVisibility(View.GONE);
        }

    }


}