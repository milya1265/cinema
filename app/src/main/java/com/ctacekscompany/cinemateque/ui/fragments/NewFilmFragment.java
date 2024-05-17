package com.ctacekscompany.cinemateque.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.ServiceLocator;
import com.ctacekscompany.cinemateque.databinding.FragmentNewfilmBinding;
import com.ctacekscompany.cinemateque.data.models.FilmEntity;
import com.ctacekscompany.cinemateque.data.repositories.FilmsRepository;

import java.util.ArrayList;

public class NewFilmFragment extends Fragment {

    private FragmentNewfilmBinding binding;

    private FilmsRepository filmsRepository;

    public NewFilmFragment() {
        super(R.layout.fragment_newfilm);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filmsRepository = ServiceLocator.getInstance().getFilmsRepository(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewfilmBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonUpload.setOnClickListener(v ->{
            String filmName = binding.editTextName.getText().toString();
            if (!TextUtils.isEmpty(filmName)){
                binding.editTextName.setError(null);
                ArrayList<Integer> posterPics = createPosterPics();
                filmsRepository.createNewFilm(new FilmEntity(filmName.trim(), posterPics.get(getRandomNumber(1, 4))));
                Navigation.findNavController(view).popBackStack();
            }
            else {
                binding.editTextName.setError(getString(R.string.error_zero_length));
            }
        });
    }

    private static ArrayList<Integer> createPosterPics() {
        ArrayList<Integer> posterPics = new ArrayList<>();
        posterPics.add(R.drawable.back_to_the_future);
        posterPics.add(R.drawable.harry_potter);
        posterPics.add(R.drawable.hobbit);
        posterPics.add(R.drawable.star_wars);
        return posterPics;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}


