package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "internautes")
public class Internaute {

    @PrimaryKey
    @NonNull
    private String email;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String region;

    @ColumnInfo(name="annee_naissance")
    private int anneeNaissance;

    public Internaute(@NonNull String email, @NonNull String nom, @NonNull String prenom, @NonNull String region, int anneeNaissance) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.region = region;
        this.anneeNaissance = anneeNaissance;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
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

    @NonNull
    public String getRegion() {
        return region;
    }

    public void setRegion(@NonNull String region) {
        this.region = region;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }
}
