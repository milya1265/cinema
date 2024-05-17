package com.ctacekscompany.cinemateque.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ctacekscompany.cinemateque.ServiceLocator;
import com.ctacekscompany.cinemateque.data.datasources.MySharedPreferences;
import com.ctacekscompany.cinemateque.data.models.Film;
import com.ctacekscompany.cinemateque.data.repositories.FilmsRepository;
import com.ctacekscompany.cinemateque.data.repositories.PreferencesRepository;

import java.util.List;
import java.util.function.Function;

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
