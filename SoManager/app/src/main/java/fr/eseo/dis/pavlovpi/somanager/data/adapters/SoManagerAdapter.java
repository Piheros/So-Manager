package fr.eseo.dis.pavlovpi.somanager.data.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pavlovpi.somanager.SoManagerActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.Film;

public class SoManagerAdapter extends RecyclerView.Adapter<SoManagerAdapter.FilmographyViewHolder>{

    private SoManagerActivity activity;

    private List<Film> films;

    private List<Integer> expandedPositions;

    public SoManagerAdapter(SoManagerActivity soManagerActivity){
        this.expandedPositions = new ArrayList<>();
        this.activity = soManagerActivity;
        setFilms(new ArrayList<Film>());
    }

    public void setFilms(List<Film> films){
        this.films = films;
    }

    @Override
    public int getItemCount(){
        return films.size();
    }

    @Override
    public FilmographyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filmography_card_layout, parent, false);
        CardView filmCardView = (CardView)filmView;
        filmCardView.setCardElevation(3 * SoManagerActivity.NEW_CARD_COUNTER++);
        return new FilmographyViewHolder(filmView);
    }


    @Override
    public void onBindViewHolder(@NonNull FilmographyViewHolder holder, final int position) {
        final Film film = films.get(position);
        holder.filmTitre.setText(film.getTitre());
        holder.filmGenre.setText(film.getGenre());
        holder.filmAnnee.setText(String.valueOf(film.getAnnee()));
        holder.filmResume.setText(film.getResume());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickFilmCard(film);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView resume = (TextView) v.findViewById(R.id.tv_film_resume);
                TextView resumeLabel = (TextView) v.findViewById(R.id.tv_film_resume_label);
                if (expandedPositions.contains(position)) {
                    resume.setVisibility(View.GONE);
                    resumeLabel.setVisibility(View.GONE);
                    expandedPositions.remove(new Integer(position));
                } else {
                    resume.setVisibility(View.VISIBLE);
                    resumeLabel.setVisibility(View.VISIBLE);
                    expandedPositions.add(position);
                }
                return true;
            }
        });
    }


    class FilmographyViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        private final TextView filmTitre;
        private final TextView filmGenre;
        private final TextView filmAnnee;
        private final TextView filmResume;

        public FilmographyViewHolder(View view){
            super(view);
            this.view = view;
            filmTitre = view.findViewById(R.id.tv_film_title);
            filmGenre = view.findViewById(R.id.tv_film_genre);
            filmAnnee = view.findViewById(R.id.tv_film_annee);
            filmResume = view.findViewById(R.id.tv_film_resume);



        }
    }
}
