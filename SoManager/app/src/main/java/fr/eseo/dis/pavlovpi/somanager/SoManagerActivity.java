package fr.eseo.dis.pavlovpi.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fr.eseo.dis.pavlovpi.somanager.data.Film;
import fr.eseo.dis.pavlovpi.somanager.data.SoManagerDatabase;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.SoManagerAdapter;

public class SoManagerActivity extends AppCompatActivity {

    public static final String FILM_EXTRA = "film_extra";
    public static int NEW_CARD_COUNTER;

    private SoManagerAdapter soManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmography);
        this.NEW_CARD_COUNTER = 0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.filmographyList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        soManagerAdapter = new SoManagerAdapter(this);
        recycler.setAdapter(soManagerAdapter);
        loadAllFilmsData();
    }

    private void loadAllFilmsData(){
        soManagerAdapter.setFilms(SoManagerDatabase.getDatabase(this).filmDao().findAllFilms());
        soManagerAdapter.notifyDataSetChanged();
    }


    public void clickFilmCard(Film film) {
        Intent intent = new Intent(this, SoManagerDetailsActivity.class);
        intent.putExtra(FILM_EXTRA, film);
        startActivity(intent);
    }


}


