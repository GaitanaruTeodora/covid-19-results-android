package com.example.tema2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tema2.database.CentruAdapter;
import com.example.tema2.database.CentruSanitar;
import com.example.tema2.database.CentruSanitarService;
import com.example.tema2.network.Callback;
import com.example.tema2.util.Pacient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CentreSanitareActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;
    private ListView lvCentre;

    private List<CentruSanitar> listCentre = new ArrayList<>();

    private ActivityResultLauncher<Intent> addCentruLauncher;

    private CentruSanitarService centruService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre_sanitare);
        initComponents();

        addCentruLauncher = getAddCentruLauncher();
        centruService = new CentruSanitarService(getApplicationContext());

        //===========SELECT==========
        centruService.getAll(getAllCentreSQLiteCallback());
        
    }

    private Callback<List<CentruSanitar>> getAllCentreSQLiteCallback() {
                return new Callback<List<CentruSanitar>>() {
            @Override
            public void runResultOnUiThread(List<CentruSanitar> centru) {
                if(centru!=null){
                    listCentre.clear();
                    listCentre.addAll(centru);
                    notifyAdapter();
                }
            }
        };
    }

    private void initComponents() {
        fabAdd = findViewById(R.id.gaitanaru_teodora_fab_centre_add);
        fabAdd.setOnClickListener(openAddCentreClickListener());
        lvCentre = findViewById(R.id.gaitanaru_teodora_lv_centre);
        addLvAdapter();
    }

    private void addLvAdapter() {
        CentruAdapter adapter = new CentruAdapter(getApplicationContext(), R.layout.lv_row_centre, listCentre, getLayoutInflater());
        lvCentre.setAdapter(adapter);
    }

    private View.OnClickListener openAddCentreClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCentruSanitarActivity.class);
                addCentruLauncher.launch(intent);
            }
        };
    }

    // -- Pas3 -- Definire ActivityLauncher pentru Add
    private ActivityResultLauncher<Intent> getAddCentruLauncher() {
        ActivityResultCallback<ActivityResult> callback = getAddCentruActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getAddCentruActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null && result.getResultCode() == RESULT_OK && result.getData() != null) {
                    CentruSanitar agent = (CentruSanitar) result.getData().getSerializableExtra(AddCentruSanitarActivity.CENTRU_KEY);
                    //inserare in baza de date
                    centruService.insert(agent, getInsertCentruSQLiteCallback());
                }
            }
        };
    }

    private Callback<CentruSanitar> getInsertCentruSQLiteCallback() {
        return new Callback<CentruSanitar>() {
            @Override
            public void runResultOnUiThread(CentruSanitar centru) {
                if (centru != null) {
                    listCentre.add(centru);
                    notifyAdapter();
                }
            }
        };
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvCentre.getAdapter();
        adapter.notifyDataSetChanged();
    }

}