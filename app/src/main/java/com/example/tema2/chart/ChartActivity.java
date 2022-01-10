package com.example.tema2.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tema2.R;
import com.example.tema2.util.Pacient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    public static final String PACIENT_KEY = "PACIENT_KEY";
    public static final String PACIENT_NEGATIV = "Negativ";
    public static final String PACIENT_POZITIV = "Pozitiv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Pacient> pacients = (List<Pacient>) getIntent().getSerializableExtra(PACIENT_KEY);
        setContentView(new ChartView(getApplicationContext(),getSource(pacients)));
    }

    private Map<String, Integer> getSource(List<Pacient> pacients) {
        if(pacients == null || pacients.isEmpty()){
            return new HashMap<>();
        }
        int nrPoz = 0;
        int nrNeg = 0;
        Map<String,Integer> source = new HashMap<>();
        for (Pacient pacient : pacients) {
            if (pacient.getListDetalii().get(0).isRezultat()) {
                nrNeg+=1;
                source.put(PACIENT_NEGATIV, nrNeg );
            } else {
                nrPoz +=1;
                source.put(PACIENT_POZITIV, nrPoz);
            }
        }
        return source;
    }
}