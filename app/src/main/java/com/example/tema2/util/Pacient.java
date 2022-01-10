package com.example.tema2.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pacient implements Serializable {
    private String nume;
    private String prenume;
    private long cnp;
    List<DetaliiTest> listDetalii = new ArrayList<>();

    public Pacient(String nume, String prenume, long cnp, List<DetaliiTest> listDetalii) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.listDetalii = listDetalii;
    }
    @Override
    public String toString() {
        return "Pacient{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", cnp=" + cnp +
                ", listDetalii=" + listDetalii +
                '}';
    }
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public List<DetaliiTest> getListDetalii() {
        return listDetalii;
    }

    public void setListDetalii(List<DetaliiTest> listDetalii) {
        this.listDetalii = listDetalii;
    }

}
