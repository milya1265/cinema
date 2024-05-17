package com.ctacekscompany.cinemateque.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.data.models.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends ListAdapter<Film, FilmsAdapter.ViewHolder> implements Filterable {

    public interface OnFilmClickListener {
        void onFilmClick(Film film, int position);
    }

    private final OnFilmClickListener onClickListener;
    private List<Film> films;
    private List<Film> nonfilteredFilms;

    ValueFilter valueFilter;

    private static final DiffUtil.ItemCallback<Film> DIFF_CALLBACK = new DiffUtil.ItemCallback<Film>() {
        @Override
        public boolean areItemsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Film oldItem, @NonNull Film newItem) {
            return oldItem.equals(newItem);
        }
    };

    public FilmsAdapter(OnFilmClickListener onClickListener) {
        super(DIFF_CALLBACK);
        this.nonfilteredFilms = new ArrayList<>();
        this.films = new ArrayList<>();

        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FilmsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Film film = films.get(position);
        holder.nameView.setText(film.getName());
        holder.posterView.setImageResource(film.getPosterImage());
        holder.itemView.setOnClickListener(v -> onClickListener.onFilmClick(film, position));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateFilmList(final List<Film> films) {
        this.nonfilteredFilms.clear();
        this.films.clear();
        this.nonfilteredFilms = films;
        this.films = films;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Film> filterList = new ArrayList<>();
                for (int i = 0; i < nonfilteredFilms.size(); i++) {
                    if ((nonfilteredFilms.get(i).getName().toLowerCase()).contains(constraint.toString().toLowerCase())) {
                        filterList.add(nonfilteredFilms.get(i));
                    }
                }
                results.count = nonfilteredFilms.size();
                results.values = filterList;
            } else {
                results.count = nonfilteredFilms.size();
                results.values = nonfilteredFilms;
            }
            return results;
        }
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            films = (List<Film>) results.values;
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final ImageView posterView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            posterView = view.findViewById(R.id.poster);
        }
    }
}
