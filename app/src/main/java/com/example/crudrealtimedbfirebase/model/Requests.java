package com.example.crudrealtimedbfirebase.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Requests implements Serializable {

    String key;

    String nama, email, deskripsi;

    public Requests() {
    }

    public Requests(String nama, String email, String deskripsi) {
        this.nama = nama;
        this.email = email;
        this.deskripsi = deskripsi;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    @NonNull
//    @Override
//    public String toString() {
//        return "" + nama + "\n" +
//                "" + email + "\n" +
//                "" + deskripsi;
//    }
}
