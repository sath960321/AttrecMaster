package com.example.attrecmaster.adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attrecmaster.R;
import com.example.attrecmaster.clases.Registro;

import java.util.ArrayList;

public class ListRegistroAdapter extends RecyclerView.Adapter<ListRegistroAdapter.RegistroViewHolder> {

    Context context;
    int resourcelayout;
    ArrayList<Registro> listaRegistro;

    public ListRegistroAdapter(Context context, int resourcelayout, ArrayList<Registro> listaRegistro) {
        this.context = context;
        this.resourcelayout = resourcelayout;
        this.listaRegistro = listaRegistro;
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourcelayout, null, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.viewAsignatura.setText(listaRegistro.get(position).getAsignatura());
        holder.viewGrupo.setText(listaRegistro.get(position).getGrupo());
        holder.viewAnio.setText(listaRegistro.get(position).getAnio());
        holder.viewFacultad.setText(listaRegistro.get(position).getFacultad().toString());
    }

    @Override
    public int getItemCount() {
        return listaRegistro.size();
    }

    public class RegistroViewHolder extends RecyclerView.ViewHolder {
        TextView viewAsignatura, viewGrupo, viewAnio, viewFacultad;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            viewAsignatura = itemView.findViewById(R.id.txtAsign);
            viewGrupo = itemView.findViewById(R.id.txtGrupo);
            viewAnio = itemView.findViewById(R.id.txtAnio);
            viewFacultad = itemView.findViewById(R.id.txtFacultad);
        }
    }
}
