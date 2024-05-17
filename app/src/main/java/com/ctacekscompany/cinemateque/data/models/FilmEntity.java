package com.ctacekscompany.cinemateque.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film_table")
public class FilmEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "film_name")
    private String name;
    private int posterImage;
    public FilmEntity(@NonNull String name, int pic) {
        this.name = name;
        this.posterImage = pic;
    }
    public FilmEntity() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPosterImage() {
        return posterImage;
    }
    public void setPosterImage(int posterImage) {
        this.posterImage = posterImage;
    }
    @NonNull
    public String getName() {
        return this.name;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Film toFilm(){
        return new Film(this.name,this.posterImage);
    }
}
