package com.example.aplikasiprogmob.Model;

public class DaftarKrs {

    private String kode;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getJlhmhs() {
        return jlhmhs;
    }

    public void setJlhmhs(String jlhmhs) {
        this.jlhmhs = jlhmhs;
    }

    private String matkul;
    private String hari;
    private String sesi;
    private String sks;
    private String dosen;
    private String jlhmhs;

    public  DaftarKrs (String kode, String matkul, String hari, String sesi, String sks, String dosen, String jlhmhs){
        this.kode = kode;
        this.matkul = matkul;
        this.hari = hari;
        this.sesi = sesi;
        this.sks = sks;
        this.dosen = dosen;
        this.jlhmhs = jlhmhs;
    }
}
