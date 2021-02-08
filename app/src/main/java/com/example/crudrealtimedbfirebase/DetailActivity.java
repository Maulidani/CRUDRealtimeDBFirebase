package com.example.crudrealtimedbfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudrealtimedbfirebase.model.Requests;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    private DatabaseReference db;
    private EditText nama, email, desk;
    private ProgressDialog loading;
    private String Hnama, Hemail, Hdesk;
    private String id, putNama, putEmail, putDesk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = FirebaseDatabase.getInstance().getReference();

        id = getIntent().getStringExtra("id");
        putNama = getIntent().getStringExtra("nama");
        putEmail = getIntent().getStringExtra("email");
        putDesk = getIntent().getStringExtra("deskripsi");

        nama = findViewById(R.id.et_nama_edit);
        email = findViewById(R.id.et_email_edit);
        desk = findViewById(R.id.et_desk_edit);

        nama.setText(putNama);
        email.setText(putEmail);
        desk.setText(putDesk);

            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        findViewById(R.id.btn_edit).setOnClickListener(v -> {
//                alertEdit();

            Hnama = nama.getText().toString();
            Hemail = email.getText().toString();
            Hdesk = desk.getText().toString();

            loading = ProgressDialog.show(DetailActivity.this,
                    null,
                    "Please Wait...",
                    true, false);

            editUser(new Requests(Hnama, Hemail, Hdesk), id);
        });

    }

//    private void alertEdit(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        // set title dialog
////        alertDialogBuilder.setTitle("Yakin Ingin Mengubah Data ?");
//
//        // set pesan dari dialog
//        alertDialogBuilder
//                .setMessage("Yakin Ingin Mengubah Data ?")
//                .setCancelable(false)
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                            loading = ProgressDialog.show(DetailActivity.this,
//                                    null,
//                                    "Please Wait...",
//                                    true, false);
//
//                            editUser(new Requests(Hnama,Hemail,Hdesk));
//
//                    }
//                })
//                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.cancel();
//                    }
//                });
//
//        // membuat alert dialog dari builder
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // menampilkan alert dialog
//        alertDialog.show();
//    }

    private void editUser(Requests requests, String id) {

        db.child("Request")
                .child(id)
                .setValue(requests)
                .addOnSuccessListener(this, aVoid -> {
                    loading.dismiss();

                    Toast.makeText(DetailActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                });
    }
}