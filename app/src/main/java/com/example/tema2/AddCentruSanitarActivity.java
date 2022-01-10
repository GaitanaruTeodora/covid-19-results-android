package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tema2.database.CentruSanitar;
import com.google.android.material.textfield.TextInputEditText;

public class AddCentruSanitarActivity extends AppCompatActivity {

    public static final String CENTRU_KEY = "CENTRU_KEY";

    //Xml components
    private TextInputEditText tietDenumire;
    private TextInputEditText tietJudet;
    private TextInputEditText tietAdresa;
    private TextInputEditText tietTelefon;
    private Button btnSalvare;

    private Intent intent;
    private CentruSanitar centruSanitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_centru_sanitar);
        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        tietDenumire = findViewById(R.id.gaitanaru_teodora_tiet_add_denumire);
        tietJudet = findViewById(R.id.gaitanaru_teodora_tiet_add_judet);
        tietAdresa = findViewById(R.id.gaitanaru_teodora_tiet_add_adresa);
        tietTelefon = findViewById(R.id.gaitanaru_teodora_tiet_add_telefon);
        btnSalvare = findViewById(R.id.gaitanaru_teodora_btn_add_centru);
        btnSalvare.setOnClickListener(getAddCentruClickListner());
    }

    private View.OnClickListener getAddCentruClickListner() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    createFromViews();
                    intent.putExtra(CENTRU_KEY, centruSanitar);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String denumire = tietDenumire.getText().toString();
        String judet = tietJudet.getText().toString();
        String adresa = tietAdresa.getText().toString();
        String telefon = tietTelefon.getText().toString();
        if( centruSanitar == null){
            centruSanitar =  new CentruSanitar(denumire, judet, adresa,telefon);
    }

    }

    private boolean isValid() {
        if (tietDenumire.getText() == null || tietDenumire.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.denumire_invalida ,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (tietJudet.getText() == null || tietJudet.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.judet_invalid ,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietAdresa.getText() == null || tietAdresa.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.adresa_invalida ,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietTelefon.getText() == null || tietTelefon.getText().toString().trim().length() !=10) {
            Toast.makeText(getApplicationContext(),
                    R.string.telefon_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }
}