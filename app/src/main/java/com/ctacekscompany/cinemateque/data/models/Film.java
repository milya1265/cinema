package com.ctacekscompany.cinemateque.data.models;

import java.util.Objects;

public class Film {

    private String name;
    private int posterImage;

    public Film(String name, int pic) {
        this.name = name;
        this.posterImage = pic;
    }

    public int getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(int posterImage) {
        this.posterImage = posterImage;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureResource() {
        return this.posterImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return posterImage == film.posterImage && name.equals(film.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, posterImage);
    }
}
