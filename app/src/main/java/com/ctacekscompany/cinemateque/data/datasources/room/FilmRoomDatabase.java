package com.ctacekscompany.cinemateque.data.datasources.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.data.models.FilmEntity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FilmEntity.class}, version = 1, exportSchema = false)
public abstract class FilmRoomDatabase extends RoomDatabase {
    public abstract FilmDao filmDao();
    private static volatile FilmRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static FilmRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FilmRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FilmRoomDatabase.class, "film_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            ArrayList<Integer> posterPics = createPosterPics();
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FilmDao dao = INSTANCE.filmDao();
                for (int i = 0; i < 100; i++) {
                    FilmEntity film = new FilmEntity("Star Wars" + i, posterPics.get(i % 4));
                    dao.insert(film);
                }
            });
        }
    };

    private static ArrayList<Integer> createPosterPics() {
        ArrayList<Integer> posterPics = new ArrayList<>();
        posterPics.add(R.drawable.back_to_the_future);
        posterPics.add(R.drawable.harry_potter);
        posterPics.add(R.drawable.hobbit);
        posterPics.add(R.drawable.star_wars);
        return posterPics;
    }

}
