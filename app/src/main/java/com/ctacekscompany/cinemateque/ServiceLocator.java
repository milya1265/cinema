package com.ctacekscompany.cinemateque;

import android.content.Context;

import com.ctacekscompany.cinemateque.data.repositories.FilmsRepository;
import com.ctacekscompany.cinemateque.data.repositories.PreferencesRepository;

public class ServiceLocator {
    private FilmsRepository filmsRepository = null;
    private PreferencesRepository preferencesRepository = null;
    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public FilmsRepository getFilmsRepository(Context context) {
        synchronized (this) {
            if (filmsRepository == null) {
                createFilmsRepository(context);
            }
            return filmsRepository;
        }
    }
    private void createFilmsRepository(Context context){
        filmsRepository = new FilmsRepository(context);
    }

    public PreferencesRepository getPreferencesRepository(Context context) {
        synchronized (this) {
            if (preferencesRepository == null) {
                createPreferencesRepository(context);
            }
            return preferencesRepository;
        }
    }
    private void createPreferencesRepository(Context context){
        preferencesRepository = new PreferencesRepository(context);
    }
}
