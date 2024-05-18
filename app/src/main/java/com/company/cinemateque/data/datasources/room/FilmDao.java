package com.company.cinemateque.data.datasources.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.company.cinemateque.data.models.FilmEntity;

import java.util.List;

@Dao
public interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FilmEntity film);
    @Query("DELETE FROM film_table")
    void deleteAll();
    @Query("SELECT * FROM film_table ORDER BY id")
    LiveData<List<FilmEntity>> getAllFilms();
}
