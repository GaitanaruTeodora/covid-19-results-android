package com.example.tema2.util;

import com.example.tema2.database.CentruSanitar;

import java.io.Serializable;
import java.util.Date;

public class DetaliiTest implements Serializable {
    private boolean rezultat;
    private String tipTest;
    private Date dataRecoltarii;

    CentruSanitar centru;

    public DetaliiTest(boolean rezultat, String tipTest, Date dataRecoltarii, CentruSanitar centru) {
        this.rezultat = rezultat;
        this.tipTest = tipTest;
        this.dataRecoltarii = dataRecoltarii;
        this.centru = centru;
    }

    public boolean isRezultat() {
        return rezultat;
    }

    public void setRezultat(boolean rezultat) {
        this.rezultat = rezultat;
    }

    public Date getDataRecoltarii() {
        return dataRecoltarii;
    }

    public void setDataRecoltarii(Date dataRecoltarii) {
        this.dataRecoltarii = dataRecoltarii;
    }

    public String getTipTest() {
        return tipTest;
    }

    public void setTipTest(String tipTest) {
        this.tipTest = tipTest;
    }

    public CentruSanitar getCentru() {
        return centru;
    }

    public void setCentru(CentruSanitar centru) {
        this.centru = centru;
    }

    @Override
    public String toString() {
        String rez;
        if(rezultat == false){
            rez = "negativ";
        } else {
            rez = "pozitiv";
        }
        return "DetaliiTest{" +
                "rezultat=" + rez +
                ", tipTest='" + tipTest + '\'' +
                ", dataRecoltarii=" + dataRecoltarii +
                ", centru=" + centru +
                '}';
    }
}
