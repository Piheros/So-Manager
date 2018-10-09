package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RoleDao {

    @Query("SELECT * FROM roles")
    public List<Role> findAllRoles();

    @Query("SELECT * FROM roles WHERE id_film=:idFilm")
    public List<Role> findRolesForFilm(int idFilm);

    @Query("SELECT * FROM roles, artistes WHERE id_film =:idFilm AND roles.id_acteur = artistes.id_artiste")
    public List<RoleAvecArtiste>findRolesWithActeursForFilm(int idFilm);
}
