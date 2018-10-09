package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "roles", primaryKeys = {"id_film","id_acteur"},
        foreignKeys = {@ForeignKey(entity = Film.class, parentColumns = "id_film", childColumns = "id_film"),
            @ForeignKey(entity = Artiste.class, parentColumns = "id_artiste", childColumns = "id_acteur")})
public class Role {

    @NonNull
    @ColumnInfo(name = "id_film")
    private int idFilm;

    @NonNull
    @ColumnInfo(name = "id_acteur")
    private int idActeur;

    @ColumnInfo(name = "nom_role")
    private String nomRole;

    public Role(@NonNull int idFilm, @NonNull int idActeur, String nomRole) {
        this.idFilm = idFilm;
        this.idActeur = idActeur;
        this.nomRole = nomRole;
    }

    @NonNull
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(@NonNull int idFilm) {
        this.idFilm = idFilm;
    }

    @NonNull
    public int getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(@NonNull int idActeur) {
        this.idActeur = idActeur;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
