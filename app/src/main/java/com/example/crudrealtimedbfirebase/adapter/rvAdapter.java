package com.example.crudrealtimedbfirebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudrealtimedbfirebase.DetailActivity;
import com.example.crudrealtimedbfirebase.R;
import com.example.crudrealtimedbfirebase.model.Requests;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder> {
    private final List<Requests> list;
    private final Context context;

    public rvAdapter(List<Requests> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Requests data = list.get(position);

        holder.nama.setText(data.getNama());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putExtra("id", data.getKey())
                        .putExtra("nama", data.getNama())
                        .putExtra("email",data.getEmail())
                        .putExtra("deskripsi",data.getDeskripsi()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nama;
        private final CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_nama);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}
