package com.example.mybookactivity.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mybookactivity.R;
import com.example.mybookactivity.databinding.BookfragmentBinding;
import com.example.mybookactivity.logic.dao.entity.BookShelf;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class BookFragment extends Fragment {

    private BookfragmentBinding binding;

    private MyBookAdapter myBookAdapter;

    public BookFragmentViewModel viewModel;

    private MyBookDeleteDialog myBookDeleteDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BookfragmentBinding.inflate(inflater,container,false);
        viewModel = ViewModelProviders.of(getActivity()).get(BookFragmentViewModel.class);




        binding.bookRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        myBookAdapter = new MyBookAdapter(getContext(),viewModel.bookShelfList);
        binding.bookRecyclerview.setAdapter(myBookAdapter);
        binding.bookRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        viewModel.refreshBookShelf();
        viewModel.bookShelfLivedata.observe(this, new Observer<List<BookShelf>>() {
            @Override
            public void onChanged(List<BookShelf> list) {
                binding.mySwipeRefrsh.setRefreshing(false);
                viewModel.bookShelfList.clear();
                viewModel.bookShelfList.addAll(list);
                myBookAdapter.notifyDataSetChanged();
            }
        });
        binding.mySwipeRefrsh.setColorSchemeColors(getResources().getColor(R.color.maincolor));
        binding.mySwipeRefrsh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    viewModel.refreshBookShelf();

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshBookShelf(RefreshBookShelfEvent event){
        viewModel.refreshBookShelf();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(myBookAdapter!=null && myBookAdapter.myBookDetailsDialog!=null){
            viewModel.refreshBookShelf();
            myBookAdapter.myBookDetailsDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))//加上判断
        {
            EventBus.getDefault().unregister(this);
        }

    }
}
