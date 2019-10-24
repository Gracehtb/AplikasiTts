package com.example.aplikasiprogmob.Model;

public class DataMahasiswa {


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    private String nim;
    private String nama;
    private String email;
    private String alamat;


    public DataMahasiswa(String nim, String nama, String email, String alamat){
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
    }
}
