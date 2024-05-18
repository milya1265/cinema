package com.company.cinemateque.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.databinding.FragmentFilmdetailsBinding;


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

