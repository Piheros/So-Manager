package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NotationDao {

    @Query("SELECT * FROM notations")
    public List<Notation> findAllNotations();

    @Query("SELECT * FROM notations WHERE id_film=:idFilm")
    public List<Notation> findNotationForFilm(int idFilm);

    @Query("SELECT * FROM internautes, notations WHERE id_film=:idFilm AND internautes.email == notations.email")
    public List<NotationAvecInternaute> findNotationsWithInternautesForFilm(int idFilm);
}
