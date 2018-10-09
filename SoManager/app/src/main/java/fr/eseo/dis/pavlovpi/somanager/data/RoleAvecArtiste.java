package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class RoleAvecArtiste {

    @Relation(parentColumn = "id_acteur", entity=Artiste.class, entityColumn = "id_artiste")
    public List<Artiste> artistes;

    @Embedded
    public Role role;
}
