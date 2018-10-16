package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ArtisteDao {

    @Query("SELECT * FROM artistes")
    public List<Artiste> findAllArtistes();

    @Query("SELECT * FROM artistes WHERE id_artiste=:idArtiste")
    public Artiste findArtisteFromId(int idArtiste);
}

