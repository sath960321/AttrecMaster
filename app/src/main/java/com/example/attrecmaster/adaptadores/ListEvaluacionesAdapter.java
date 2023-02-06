package com.example.attrecmaster.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attrecmaster.R;
import com.example.attrecmaster.clases.Evaluacion;
import com.example.attrecmaster.clases.Registro;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListEvaluacionesAdapter extends RecyclerView.Adapter<ListEvaluacionesAdapter.EvaluacionesViewHolder> {
    Context context;
    int resourcelayout;
    ArrayList<Evaluacion> listaEvaluaciones;

    public ListEvaluacionesAdapter(Context context, int resourcelayout, ArrayList<Evaluacion> listaEvaluaciones) {
        this.context = context;
        this.resourcelayout = resourcelayout;
        this.listaEvaluaciones = listaEvaluaciones;
    }

    @NonNull
    @Override
    public EvaluacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourcelayout, null, false);
        return new EvaluacionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluacionesViewHolder holder, int position) {
        holder.txtTipoEvaluacion.setText(listaEvaluaciones.get(position).getTipo());
        holder.txtFechaEvaluacion.setText(listaEvaluaciones.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaEvaluaciones.size();
    }

    public class EvaluacionesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTipoEvaluacion, txtFechaEvaluacion;
        public EvaluacionesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTipoEvaluacion = itemView.findViewById(R.id.txtTipoEvaluacion);
            txtFechaEvaluacion = itemView.findViewById(R.id.txtFechaEvaluacion);
        }
    }
}
