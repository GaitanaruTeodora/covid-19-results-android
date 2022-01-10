package com.example.tema2.database;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tema2.R;

import java.util.List;

public class CentruAdapter extends ArrayAdapter<CentruSanitar> {
    private Context context;
    private int resource;
    private List<CentruSanitar> listaCentre;
    private LayoutInflater inflater;

    public CentruAdapter(@NonNull Context context, int resource, @NonNull List<CentruSanitar> objects,LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listaCentre = objects;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        CentruSanitar centru = listaCentre.get(position);
        if (centru == null) {
            return view;
        }
        addCentruDenumire(view, centru.getDenumire());
        addCentruJudet(view, centru.getJudet());
        addCentruAdresa(view, centru.getAdresa());

        addCentruTelefon(view, centru.getTelefon());
        return view;
    }
    private void addCentruDenumire(View view, String denumire) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_centru_denumire);
        populateTextViewContent(textView, denumire);
    }
    private void addCentruJudet(View view, String judet) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_centru_judet);
        populateTextViewContent(textView, judet);
    }
    private void addCentruAdresa(View view, String adresa) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_centru_adresa);
        populateTextViewContent(textView, adresa);
    }
    private void addCentruTelefon(View view, String telefon) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_centru_telefon);
        populateTextViewContent(textView, telefon);
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.default_value);
        }
    }

}
