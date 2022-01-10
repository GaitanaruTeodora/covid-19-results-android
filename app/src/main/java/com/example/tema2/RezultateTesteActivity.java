package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import com.example.tema2.chart.ChartActivity;
import com.example.tema2.chart.ChartActivity;
import com.example.tema2.network.AsyncTaskRunner;
import com.example.tema2.network.Callback;
import com.example.tema2.network.HttpManager;
import com.example.tema2.util.Pacient;
import com.example.tema2.util.PacientAdapter;
import com.example.tema2.util.PacientJsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RezultateTesteActivity extends AppCompatActivity {

    private final static String REZULTATE_TESTE_URL = "https://jsonkeeper.com/b/2T16";
    private ListView lvRezultate;
    private List<Pacient> listaPacienti = new ArrayList<>();
    private Button btnChart;
    private final AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultate_teste);

        initComponents();
        loadPacientiFromHttp();

    }

    private void loadPacientiFromHttp() {
        Callable<String> asyncOperation = new HttpManager(REZULTATE_TESTE_URL);
        Callback<String> mainThreadOperation = getMainThreadOperation();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> getMainThreadOperation() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                listaPacienti.addAll(PacientJsonParser.fromJson(result));
                //Toast.makeText(getApplicationContext(), listaPacienti.size(), Toast.LENGTH_SHORT).show();
                notifyLvPacientiAdapter();
            }
        };
    }

    private void notifyLvPacientiAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvRezultate.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void initComponents() {
        lvRezultate = findViewById(R.id.gaitanaru_teodora_lv_rezultateTeste);
        addLvPacientiAdapter();
        btnChart = findViewById(R.id.gaitanaru_teodora_btn_vizualizareStatistici);
        btnChart.setOnClickListener(getOpenStatisticiClickListener());
    }

    private View.OnClickListener getOpenStatisticiClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                intent.putExtra(ChartActivity.PACIENT_KEY, (Serializable) listaPacienti);
                startActivity(intent);
            }
        };
    }

    private void addLvPacientiAdapter() {
//        ArrayAdapter<Pacient> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, listaPacienti);
        PacientAdapter adapter = new PacientAdapter(getApplicationContext(), R.layout.lv_row_rezultate, listaPacienti, getLayoutInflater());
        lvRezultate.setAdapter(adapter);
    }
}