package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "artistes")
public class Artiste {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_artiste")
    private int idArtiste;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @ColumnInfo(name = "annee_naissance")
    private int anneeNaissance;

    public Artiste(@NonNull int idArtiste, @NonNull String nom, @NonNull String prenom, int anneeNaissance) {
        this.idArtiste = idArtiste;
        this.nom = nom;
        this.prenom = prenom;
        this.anneeNaissance = anneeNaissance;
    }

    @NonNull
    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(@NonNull int idArtiste) {
        this.idArtiste = idArtiste;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NonNull String prenom) {
        this.prenom = prenom;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }
}
