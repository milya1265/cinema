package com.ctacekscompany.cinemateque.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ctacekscompany.cinemateque.FilmWorker;
import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.databinding.FragmentFilmdetailsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class FilmDetailsFragment extends Fragment {
    private FragmentFilmdetailsBinding binding;
    private AnimationDrawable animation;

    public FilmDetailsFragment() {
        super(R.layout.fragment_filmdetails);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmdetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint({"QueryPermissionsNeeded", "SetTextI18n"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            binding.textViewFilmName.setText(getArguments().getString("filmName"));
//            binding.filmPoster.setImageResource(getArguments().getInt("posterId"));
        }

        binding.filmPoster.setBackgroundResource(R.drawable.images);
        animation = (AnimationDrawable) binding.filmPoster.getBackground();

        binding.filmPoster.setOnClickListener(v -> {animation.start();});
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

