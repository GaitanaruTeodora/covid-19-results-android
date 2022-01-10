package com.example.tema2.util;

import android.util.Log;

import com.example.tema2.database.CentruSanitar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacientJsonParser {

    public static final String PACIENT_NUME = "nume";
    public static final String PACIENT_PRENUME = "prenume";
    public static final String PACIENT_CNP = "cnp";
    public static final String PACIENT_REZULTAT = "rezultat";
    public static final String PACIENT_TIP_TEST = "tipTest";
    public static final String PACIENT_DATA = "dataRecoltarii";


    public static List<Pacient> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            for(int i = 0 ;i < array.length();i++){
                JSONObject object = array.getJSONObject(i);
                System.out.println(object);
            }

            return readPacienti(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static ArrayList<Pacient> readPacienti(JSONArray array) throws JSONException {
        ArrayList<Pacient> listPacienti = new ArrayList<>();
        for( int i = 0 ; i < array.length();i++){
            Pacient pacient = readPacient(array.getJSONObject(i));
            listPacienti.add(pacient);
        }
        return listPacienti;
    }
    private static Pacient readPacient(JSONObject object) throws JSONException {

//        String denumire, String judet, String adresa, String telefon
        JSONObject centru_json = object.getJSONObject("detaliiTest").getJSONObject("centruRecoltare");
        CentruSanitar centru = new CentruSanitar(centru_json.getString("denumire"),centru_json.getString("judet") , centru_json.getString("adresa"), centru_json.getString("telefon"));

        JSONObject detaliu_json = object.getJSONObject("detaliiTest");
        List<DetaliiTest> listaDetalii = new ArrayList<>();
        boolean rezultat = Boolean.parseBoolean(detaliu_json.getString(PACIENT_REZULTAT));
        String tipTest = detaliu_json.getString(PACIENT_TIP_TEST);
        Date dataRecoltarii = DateConverter.fromString(detaliu_json.getString(PACIENT_DATA));
        DetaliiTest detalii = new DetaliiTest(rezultat,tipTest,dataRecoltarii, centru);
        listaDetalii.add(detalii);

        String nume = object.getString(PACIENT_NUME);
        String prenume = object.getString(PACIENT_PRENUME);
        long cnp =Long.parseLong(object.getString(PACIENT_CNP));
        return new Pacient(nume, prenume,cnp,listaDetalii);

    }
}
