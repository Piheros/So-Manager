package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PaysDao {

    @Query("SELECT * FROM pays")
    public List<Pays> findAllPays();

    @Query("SELECT * FROM pays WHERE code=:codePays")
    public Pays findPaysFromCode(String codePays);

}
