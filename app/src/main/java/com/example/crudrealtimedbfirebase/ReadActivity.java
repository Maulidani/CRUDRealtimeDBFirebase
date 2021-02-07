package com.example.crudrealtimedbfirebase;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudrealtimedbfirebase.adapter.rvAdapter;
import com.example.crudrealtimedbfirebase.model.Requests;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    private ArrayList<Requests> listReq;
    private rvAdapter adapter;
    private RecyclerView rv;
    private ProgressDialog loading;
//    private FloatingActionButton fab_add;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        rv = findViewById(R.id.rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(ReadActivity.this,null,"Please wait..",true,false);

        db.child("Request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listReq = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : snapshot.getChildren()){

                    Requests requests = noteDataSnapshot.getValue(Requests.class);
//                    requests.setKey(noteDataSnapshot.getKey());

                    listReq.add(requests);
                }

                adapter = new rvAdapter(listReq, ReadActivity.this);
                rv.setAdapter(adapter);
                loading.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                System.out.println(error.getDetails()+" "+error.getMessage());
                loading.dismiss();
            }
        });
    }
}