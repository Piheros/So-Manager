package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface InternauteDao {

    @Query("SELECT * FROM internautes")
    public List<Internaute> findAllInternautes();
}
