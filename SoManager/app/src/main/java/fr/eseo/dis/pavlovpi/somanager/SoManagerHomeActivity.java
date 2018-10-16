package fr.eseo.dis.pavlovpi.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SoManagerHomeActivity extends AppCompatActivity {

    private Button btnVisitor;
    private Button btnJury;
    private Button btnComm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnVisitor = findViewById(R.id.button_visitor);
        btnJury = findViewById(R.id.button_Jury);
        btnComm = findViewById(R.id.button_comm);

        btnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SoManagerHomeActivity.this, SoManagerActivity.class);
                startActivity(myIntent);
            }
        });

        btnJury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SoManagerHomeActivity.this, SoManagerIdentificationActivity.class);
                startActivity(myIntent);
            }
        });

        btnComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SoManagerHomeActivity.this, SoManagerIdentificationActivity.class);
                startActivity(myIntent);
            }
        });

        btnComm.setVisibility(View.VISIBLE);
        btnJury.setVisibility(View.VISIBLE);
        btnVisitor.setVisibility(View.VISIBLE);
    }

}
