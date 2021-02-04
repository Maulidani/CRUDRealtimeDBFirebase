package com.example.crudrealtimedbfirebase;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Requests implements Serializable {

    String nama,email,deskripsi;

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

    @NonNull
    @Override
    public String toString() {
        return ""+nama+"\n"+
                ""+email+"\n"+
                ""+deskripsi;
    }
}
