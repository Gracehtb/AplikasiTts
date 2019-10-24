package com.example.aplikasiprogmob.Model;

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

    private String nidn;
    private String namadosen;
    private String gelar;
    private String email;
    private String alamat;

    public DataDosen(String nidn, String namadosen, String gelar, String email, String alamat){
        this.nidn = nidn;
        this.namadosen = namadosen;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
    }
}
