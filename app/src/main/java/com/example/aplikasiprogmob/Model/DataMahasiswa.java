package com.example.aplikasiprogmob.Model;

import  com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataMahasiswa {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamamhs() {
        return namamhs;
    }

    public void setNamamhs(String namamhs) {
        this.namamhs = namamhs;
    }

    public String getEmailmhs() {
        return emailmhs;
    }

    public void setEmailmhs(String emailmhs) {
        this.emailmhs = emailmhs;
    }

    public String getAlamatmhs() {
        return alamatmhs;
    }

    public void setAlamatmhs(String alamatmhs) {
        this.alamatmhs = alamatmhs;
    }

    public String getFotomhs() {
        return fotomhs;
    }

    public void setFotomhs(String fotomhs) {
        this.fotomhs = fotomhs;
    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nim")
    @Expose
    private String nim;

    @SerializedName("namamhs")
    @Expose
    private String namamhs;

    @SerializedName("emailmhs")
    @Expose
    private String emailmhs;

    @SerializedName("alamatmhs")
    @Expose
    private String alamatmhs;

    @SerializedName("fotomhs")
    @Expose
    private String fotomhs;



    public DataMahasiswa(String id, String nim, String namamhs, String emailmhs, String alamatmhs, String fotomhs){
        this.id = id;
        this.nim = nim;
        this.namamhs = namamhs;
        this.emailmhs = emailmhs;
        this.alamatmhs = alamatmhs;
        this.fotomhs = fotomhs;
    }
}
