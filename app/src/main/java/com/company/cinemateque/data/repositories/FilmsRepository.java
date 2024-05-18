package com.company.cinemateque.data.repositories;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.company.cinemateque.data.datasources.room.FilmRoomDatabase;
import com.company.cinemateque.data.models.Film;
import com.company.cinemateque.data.models.FilmEntity;
import com.company.cinemateque.data.datasources.room.FilmDao;

import java.util.List;
import java.util.stream.Collectors;

public class FilmsRepository {

    private final FilmDao mFilmDao;
    private final LiveData<List<Film>> mAllFilms;

    private final Context context;

    FilmRoomDatabase roomDatabase;
    public FilmsRepository(Context applicationContext) {
        context = applicationContext;
        roomDatabase = FilmRoomDatabase.getDatabase(context);
        mFilmDao = FilmRoomDatabase.getDatabase(context).filmDao();
        mAllFilms = Transformations.map(mFilmDao.getAllFilms(), entities -> entities.stream().map(FilmEntity::toFilm).collect(Collectors.toList()));
    }
    public LiveData<List<Film>> getAllFilms() {
        return mAllFilms;
    }

    public void createNewFilm(FilmEntity film) {
        FilmRoomDatabase.databaseWriteExecutor.execute(() -> {
            long successAnswer;
            successAnswer = mFilmDao.insert(new FilmEntity(film.getName(), film.getPosterImage()));
            if (successAnswer >= 0) {
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(context, "Success film insert!", Toast.LENGTH_SHORT).show());
                    Log.i("CinApp", "Success film insert! " + film.getName());
            }
        });

    }
}
