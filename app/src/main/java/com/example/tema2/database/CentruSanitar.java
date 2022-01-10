package com.example.tema2.database;
import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "centreSanitare")
public class CentruSanitar implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "denumire")
    private String denumire;

    @ColumnInfo(name = "judent")
    private String judet;

    @ColumnInfo(name = "adresa")
    private String adresa;

    @ColumnInfo(name = "telefon")
    private String telefon;

    public CentruSanitar(String denumire, String judet, String adresa, String telefon) {
        this.denumire = denumire;
        this.judet = judet;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    @Ignore
    public CentruSanitar(long id, String denumire, String judet, String adresa, String telefon) {
        this.id = id;
        this.denumire = denumire;
        this.judet = judet;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "CentruSanitar{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", judet='" + judet + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
