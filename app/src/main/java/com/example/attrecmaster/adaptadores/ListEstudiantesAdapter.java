package com.example.attrecmaster.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attrecmaster.R;
import com.example.attrecmaster.clases.Estudiante;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListEstudiantesAdapter extends RecyclerView.Adapter<ListEstudiantesAdapter.EstudiantesViewHolder> {
    Context context;
    int resourceLayout;
    ArrayList<Estudiante> listaEstudiante;

    public ListEstudiantesAdapter(Context context, int resourceLayout, ArrayList<Estudiante> listaEstudiante) {
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.listaEstudiante = listaEstudiante;
    }

    @NonNull
    @Override
    public EstudiantesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceLayout, null, false);
        return new EstudiantesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudiantesViewHolder holder, int position) {
        holder.idenEstu.setText(String.valueOf(listaEstudiante.get(position).getIdestudiante()));
        holder.txtNombre.setText(listaEstudiante.get(position).getNombre());
        holder.txtCI.setText(listaEstudiante.get(position).getCi());
        holder.txtSexo.setText(listaEstudiante.get(position).getSexo());
    }

    @Override
    public int getItemCount() {
        return listaEstudiante.size();
    }

    public class EstudiantesViewHolder extends RecyclerView.ViewHolder {
        TextView idenEstu, txtNombre, txtCI, txtSexo;

        public EstudiantesViewHolder(@NonNull View itemView) {
            super(itemView);
            idenEstu = itemView.findViewById(R.id.idenEstu);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCI = itemView.findViewById(R.id.txtCI);
            txtSexo = itemView.findViewById(R.id.txtSexo);
        }
    }
}
