package com.example.crudrealtimedbfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudrealtimedbfirebase.model.Requests;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //    private static final String TAG = "tamvan";
    private DatabaseReference db;
    private EditText nama, email, desk;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lihat = findViewById(R.id.btn_lihat);
        lihat.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReadActivity.class)));

        db = FirebaseDatabase.getInstance().getReference();

        nama = findViewById(R.id.et_nama);
        email = findViewById(R.id.et_email);
        desk = findViewById(R.id.et_desk);

        findViewById(R.id.btn_simpan).setOnClickListener(v -> {
            String Hnama = nama.getText().toString();
            String Hemail = email.getText().toString();
            String Hdesk = desk.getText().toString();

            if (Hnama.isEmpty()) {
                nama.setError("Silahkan masukkan nama");
                nama.requestFocus();
            } else if (Hemail.isEmpty()) {
                email.setError("Silahkan masukkan email");
                email.requestFocus();
            } else if (Hdesk.isEmpty()) {
                desk.setError("Silahkan masukkan deksripsi");
                desk.requestFocus();
            } else {
                loading = ProgressDialog.show(MainActivity.this,
                        null,
                        "Please Wait...",
                        true, false);

                submitUser(new Requests(Hnama,Hemail,Hdesk));

            }
        });

    }

    private void submitUser(Requests requests) {
        db.child("Request")
//                .child("request_satu")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, aVoid -> {
                    loading.dismiss();

                    nama.setText("");
                    email.setText("");
                    desk.setText("");

                    Toast.makeText(MainActivity.this, "Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                });
    }
}
