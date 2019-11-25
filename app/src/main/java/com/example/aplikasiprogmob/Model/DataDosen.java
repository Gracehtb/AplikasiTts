package com.example.aplikasiprogmob.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDosen {

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNamadosen() {
        return namadosen;
    }

    public void setNamadosen(String namadosen) {
        this.namadosen = namadosen;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nidn")
    @Expose
    private String nidn;

    @SerializedName("namaDosen")
    @Expose
    private String namadosen;

    @SerializedName("gelar")
    @Expose
    private String gelar;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("foto")
    @Expose
    private String foto;

    public DataDosen(String id, String nidn, String namadosen, String gelar, String email, String alamat, String foto) {
        this.id = id;
        this.nidn = nidn;
        this.namadosen = namadosen;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;

    }
}
