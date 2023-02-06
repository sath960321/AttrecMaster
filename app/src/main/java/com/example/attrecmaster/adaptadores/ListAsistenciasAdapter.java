package com.example.attrecmaster.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attrecmaster.R;
import com.example.attrecmaster.clases.Asistencia;
import com.example.attrecmaster.clases.Registro;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListAsistenciasAdapter extends RecyclerView.Adapter<ListAsistenciasAdapter.AsistenciasViewHolder> {
    Context context;
    int resourcelayout;
    ArrayList<Asistencia> listaAsistencias;

    public ListAsistenciasAdapter(Context context, int resourcelayout, ArrayList<Asistencia> listaAsistencias) {
        this.context = context;
        this.resourcelayout = resourcelayout;
        this.listaAsistencias = listaAsistencias;
    }

    @NonNull
    @Override
    public AsistenciasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourcelayout, null, false);
        return new AsistenciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsistenciasViewHolder holder, int position) {
        holder.txtFechaAsistencia.setText(listaAsistencias.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaAsistencias.size();
    }

    public class AsistenciasViewHolder extends RecyclerView.ViewHolder {
        TextView txtFechaAsistencia;

        public AsistenciasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFechaAsistencia = itemView.findViewById(R.id.txtFechaAsistencia2);
        }
    }
}
