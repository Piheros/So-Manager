package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pays")
public class Pays {

    @PrimaryKey
    @NonNull
    private String code;

    @NonNull
    private String nom;

    @NonNull
    private String langue;

    public Pays(@NonNull  String code, @NonNull String nom, @NonNull String langue) {
        this.code = code;
        this.nom = nom;
        this.langue = langue;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull  String code) {
        this.code = code;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getLangue() {
        return langue;
    }

    public void setLangue(@NonNull String langue) {
        this.langue = langue;
    }
}
