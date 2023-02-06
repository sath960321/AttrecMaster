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
import com.example.attrecmaster.clases.Estudiante;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAddAsistenciasAdapter extends RecyclerView.Adapter<ListAddAsistenciasAdapter.AddAsistenciasViewHolder> {
    Context context;
    int resourcelayout;
    ArrayList<Estudiante> listaEstudianteAsistencias;

    public ListAddAsistenciasAdapter(Context context, int resourcelayout, ArrayList<Estudiante> listaEstudianteAsistencias) {
        this.context = context;
        this.resourcelayout = resourcelayout;
        this.listaEstudianteAsistencias = listaEstudianteAsistencias;
    }

    @NonNull
    @Override
    public AddAsistenciasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourcelayout, null, false);
        return new AddAsistenciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAsistenciasViewHolder holder, int position) {
        holder.idenEstu2.setText(String.valueOf(listaEstudianteAsistencias.get(position).getIdestudiante()));
        holder.txtNombre2.setText(listaEstudianteAsistencias.get(position).getNombre());
        holder.txtCI2.setText(listaEstudianteAsistencias.get(position).getCi());
        holder.txtSexo2.setText(listaEstudianteAsistencias.get(position).getSexo());
    }

    @Override
    public int getItemCount() {
        return listaEstudianteAsistencias.size();
    }

    public class AddAsistenciasViewHolder extends RecyclerView.ViewHolder {
        TextView idenEstu2, txtNombre2, txtCI2, txtSexo2;
        public AddAsistenciasViewHolder(@NonNull View itemView) {
            super(itemView);
            idenEstu2 = itemView.findViewById(R.id.idenEstu2);
            txtNombre2 = itemView.findViewById(R.id.txtNombre2);
            txtCI2 = itemView.findViewById(R.id.txtCI2);
            txtSexo2 = itemView.findViewById(R.id.txtSexo2);
        }
    }
}
