package fr.eseo.dis.pavlovpi.somanager.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class NotationAvecInternaute {

    @Embedded
    public Notation notation;

    @Relation(parentColumn = "email", entityColumn = "email", entity = Internaute.class)
    public List<Internaute> internautes;
}
