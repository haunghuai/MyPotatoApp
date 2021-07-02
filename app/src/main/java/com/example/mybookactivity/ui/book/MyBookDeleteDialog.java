package com.example.mybookactivity.ui.book;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mybookactivity.databinding.BookDeletePopitemBinding;

public class MyBookDeleteDialog extends Dialog {

    private BookDeletePopitemBinding binding;
    public MyBookDeleteDialog(@NonNull Context context) {
        super(context);
    }

    public MyBookDeleteDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyBookDeleteDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BookDeletePopitemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
