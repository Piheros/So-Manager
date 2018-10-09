package fr.eseo.dis.pavlovpi.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fr.eseo.dis.pavlovpi.somanager.data.Film;
import fr.eseo.dis.pavlovpi.somanager.data.SoManagerDatabase;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.SoManagerNotationAdapter;

public class SoManagerNotationActivity extends AppCompatActivity {

    private SoManagerNotationAdapter soManagerNotationAdapter;
    private int idFilm;
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_notation);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        film = (Film) data.getParcelable(SoManagerActivity.FILM_EXTRA);
        idFilm = film.getIdFilm();
        RecyclerView recycler = findViewById(R.id.notationList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        soManagerNotationAdapter = new SoManagerNotationAdapter(this);
        recycler.setAdapter(soManagerNotationAdapter);
        loadNotationDetails();
    }

    private void loadNotationDetails(){
        soManagerNotationAdapter.setNotations(SoManagerDatabase.getDatabase(this).notationDao().findNotationsWithInternautesForFilm(idFilm));
        soManagerNotationAdapter.notifyDataSetChanged();
    }

}
