package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "films", foreignKeys = {@ForeignKey(entity= Artiste.class, parentColumns = "id_artiste", childColumns="id_realisateur"),
        @ForeignKey(entity=Pays.class, parentColumns = "code", childColumns = "code_pays")})

public class Film implements Parcelable{

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>(){
        public Film createFromParcel(Parcel source){
            return new Film(source);
        }

        public Film[] newArray(int size){
            return new Film[size];
        }
    };

    @PrimaryKey
    @ColumnInfo(name="id_film")
    private int idFilm;

    @NonNull
    private String titre;
    @NonNull
    private int annee;
    @NonNull
    @ColumnInfo(name="id_realisateur")
    private int idRealisateur;
    @NonNull
    private String genre;
    private String resume;
    @NonNull
    @ColumnInfo(name="code_pays")
    private String codePays;

    public Film(@NonNull int idFilm, @NonNull String titre, @NonNull int annee, @NonNull int idRealisateur, @NonNull String genre, String resume, @NonNull String codePays){
        this.idFilm = idFilm;
        this.titre = titre;
        this.annee = annee;
        this.idRealisateur = idRealisateur;
        this.genre = genre;
        this.resume = resume;
        this.codePays = codePays;
    }

    @Ignore
    public Film(Parcel in){
        this.idFilm = in.readInt();
        this.titre = in.readString();
        this.annee = in.readInt();
        this.idRealisateur = in.readInt();
        this.genre = in.readString();
        this.resume = in.readString();
        this.codePays = in.readString();
    }

    @NonNull
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(@NonNull int idFilm) {
        this.idFilm = idFilm;
    }

    @NonNull
    public String getTitre() {
        return titre;
    }

    public void setTitre(@NonNull String titre) {
        this.titre = titre;
    }

    @NonNull
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(@NonNull int annee) {
        this.annee = annee;
    }

    @NonNull
    public int getIdRealisateur() {
        return idRealisateur;
    }

    public void setIdRealisateur(@NonNull int idRealisateur) {
        this.idRealisateur = idRealisateur;
    }

    @NonNull
    public String getGenre() {
        return genre;
    }

    public void setGenre(@NonNull String genre) {
        this.genre = genre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @NonNull
    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(@NonNull String codePays) {
        this.codePays = codePays;
    }

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.idFilm);
        dest.writeString(this.titre);
        dest.writeInt(this.annee);
        dest.writeInt(this.idRealisateur);
        dest.writeString(this.genre);
        dest.writeString(this.resume);
        dest.writeString(this.codePays);
    }
}
