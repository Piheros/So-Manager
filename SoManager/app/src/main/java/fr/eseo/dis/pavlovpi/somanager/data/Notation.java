package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notations", primaryKeys ={"id_film","email"} ,
        foreignKeys = {@ForeignKey(entity=Film.class, parentColumns = "id_film", childColumns = "id_film"),
                @ForeignKey(entity = Internaute.class, parentColumns = "email",childColumns = "email")})
public class Notation {

    @NonNull
    @ColumnInfo(name="id_film")
    private int idFilm;

    @NonNull
    private String email;

    @NonNull
    private int note;

    public Notation(@NonNull int idFilm, @NonNull String email, @NonNull int note) {
        this.idFilm = idFilm;
        this.email = email;
        this.note = note;
    }

    @NonNull
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(@NonNull int idFilm) {
        this.idFilm = idFilm;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public int getNote() {
        return note;
    }

    public void setNote(@NonNull int note) {
        this.note = note;
    }
}
