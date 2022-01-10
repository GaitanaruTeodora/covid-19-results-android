package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnRezultate;
    private Button btnCentre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        btnRezultate = findViewById(R.id.gaitanaru_teodora_btn_main_rezultateTeste);
        btnCentre = findViewById(R.id.gaitanaru_teodora_btn_main_centreSanitare);
        btnRezultate.setOnClickListener( openListaRezultateClickListener());
        btnCentre.setOnClickListener(openListaCentreClickListener());
    }

    private View.OnClickListener openListaCentreClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CentreSanitareActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener openListaRezultateClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RezultateTesteActivity.class);
                startActivity(intent);
            }
        };
    }
}