package com.company.cinemateque.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.cinemateque.ServiceLocator;
import com.company.cinemateque.data.models.Film;
import com.company.cinemateque.data.repositories.FilmsRepository;
import com.company.cinemateque.data.repositories.PreferencesRepository;

import java.util.List;

public class FilmListViewModel extends AndroidViewModel {
    public LiveData<List<Film>> mAllWords;

    public MutableLiveData<String> _userName;
//    public LiveData<String> userName = _userName;

    public static final String TAG = FilmListViewModel.class.getSimpleName();

    public FilmListViewModel(Application application) {
        super(application);
        FilmsRepository mRepository = ServiceLocator.getInstance().getFilmsRepository(application.getApplicationContext());
        mAllWords = mRepository.getAllFilms();

        PreferencesRepository preferencesRepository = ServiceLocator.getInstance().getPreferencesRepository(application.getApplicationContext());
        _userName = new MutableLiveData<>("");
        _userName.setValue(preferencesRepository.out("USERNAME"));

    }
}
