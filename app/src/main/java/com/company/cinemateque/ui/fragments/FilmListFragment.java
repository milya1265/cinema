package com.company.cinemateque.ui.fragments;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.company.cinemateque.ui.adapters.FilmsAdapter;

import com.ctacekscompany.cinemateque.R;

import com.ctacekscompany.cinemateque.databinding.FragmentFilmlistBinding;
import com.company.cinemateque.ui.viewmodels.FilmListViewModel;


public class FilmListFragment extends Fragment {
    private FragmentFilmlistBinding binding;

    private FilmListViewModel filmListViewModel;

    private FilmsAdapter filmsAdapter;

    private static final String TAG = FilmListFragment.class.getSimpleName();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filmListViewModel = new ViewModelProvider(this).get(FilmListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmlistBinding.inflate(inflater, container, false);

        filmsAdapter = new FilmsAdapter( (film, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("filmName", film.getName());
            bundle.putInt("posterId", film.getPosterImage());

            Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_filmInfoFragment, bundle);

            Log.i(TAG, "Был выбран фильм: " + film.getName() + " " + film.getPosterImage());
        });

        return binding.getRoot();
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.recyclerView.setAdapter(filmsAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setHasFixedSize(false);

        binding.swipeContainer.setOnRefreshListener(() -> {
            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            Log.i(TAG, "Swipe!");
            binding.swipeContainer.setRefreshing(false);
        });

        binding.search.setActivated(true);
        binding.search.onActionViewExpanded();
        binding.search.clearFocus();

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filmsAdapter.getFilter().filter(newText);
//                filmListViewModel.mAllWords.getValue().stream().filter(new Predicate<Film>() {
//                    @Override
//                    public boolean test(Film film) {
//                        return false;
//                    }
//                });

                return false;
            }
        });

        filmListViewModel.mAllWords.observe(getViewLifecycleOwner(), films -> {
            filmsAdapter.updateFilmList(films);
            binding.progressBar.setVisibility(View.GONE);
        });

        filmListViewModel._userName.observe(getViewLifecycleOwner(), userName -> {
            binding.textView.setText(getString(R.string.hello_client) + " " + userName);
        });

        binding.buttonLogin.setOnClickListener(v -> {
                    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment);
                }
        );
        binding.buttonSettings.setOnClickListener(v -> {
                    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingsFragment);
                }
        );

        binding.floatingButton.setOnClickListener(v ->{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_newFilmFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}