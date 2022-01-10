package com.example.tema2.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.tema2.R;

import java.util.Date;
import java.util.List;

public class PacientAdapter extends ArrayAdapter<Pacient> {
    private Context context;
    private int resource;
    private List<Pacient> pacientList;
    private LayoutInflater inflater;

    public PacientAdapter(@NonNull Context context, int resource, @NonNull List<Pacient> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.pacientList = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Pacient pacient = pacientList.get(position);
        if (pacient == null) {
            return view;
        }
        addPacientName(view, pacient.getNume() + " " + pacient.getPrenume());
        addPacientCnp(view, pacient.getCnp());
        if(pacient.getListDetalii().get(0).isRezultat())
        {
            addPacientRezultat(view,"negativ" );
            ImageView img = view.findViewById(R.id.gaitanaru_teodora_iv_picture);
            img.setImageResource(R.drawable.no_virus);
        }
        else{
            addPacientRezultat(view,"pozitiv" );
            ImageView img = view.findViewById(R.id.gaitanaru_teodora_iv_picture);
            img.setImageResource(R.drawable.virus);
        }

        addPacientDate(view,DateConverter.fromDate(pacient.getListDetalii().get(0).getDataRecoltarii()).toString() );
        addPacientCentru(view, pacient.getListDetalii().get(0).getCentru().getDenumire());
        return view;

    }

    private void addPacientName(View view, String nume) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_pacient_nume);
        populateTextViewContent(textView, nume);
    }

    private void addPacientRezultat(View view, String rezultat) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_pacient_rezultat);
        populateTextViewContent(textView, rezultat);
    }

    private void addPacientDate(View view, String dataRezoltarii) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_pacient_data_recoltarii);
        populateTextViewContent(textView, String.valueOf(dataRezoltarii));
    }

    private void addPacientCnp(View view, long cnp) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_pacient_cnp);
        populateTextViewContent(textView, String.valueOf(cnp));
    }
    private void addPacientCentru(View view, String centru) {
        TextView textView = view.findViewById(R.id.gaitanaru_teodora_tv_row_pacient_centru);
        populateTextViewContent(textView, centru);

    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.default_value);
        }
    }
}
