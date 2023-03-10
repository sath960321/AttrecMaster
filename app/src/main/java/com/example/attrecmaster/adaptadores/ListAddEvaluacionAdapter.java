package com.example.attrecmaster.adaptadores;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attrecmaster.R;
import com.example.attrecmaster.clases.Estudiante;
import com.example.attrecmaster.clases.EstudiantesEvaluados;
import com.example.attrecmaster.clases.Evaluacion;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ListAddEvaluacionAdapter extends RecyclerView.Adapter<ListAddEvaluacionAdapter.AddEvaluacionViewHolder> {
    Context context;
    int resourcelayout;
    ArrayList<EstudiantesEvaluados> listaEstudianteEvaluacion;

    public ListAddEvaluacionAdapter(Context context, int resourcelayout, ArrayList<EstudiantesEvaluados> listaEstudianteEvaluacion) {
        this.context = context;
        this.resourcelayout = resourcelayout;
        this.listaEstudianteEvaluacion = listaEstudianteEvaluacion;
    }

    @NonNull
    @Override
    public AddEvaluacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourcelayout, null, false);
        return new AddEvaluacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddEvaluacionViewHolder holder, int position) {
        holder.idenEstu1.setText(String.valueOf(listaEstudianteEvaluacion.get(position).getIdestudiante()));
        holder.txtNombre1.setText(listaEstudianteEvaluacion.get(position).getNombre());
        holder.txtCI1.setText(listaEstudianteEvaluacion.get(position).getCi());
        holder.txtSexo1.setText(listaEstudianteEvaluacion.get(position).getSexo());
        holder.EditTextValorEvaluacion1.setText(String.valueOf(listaEstudianteEvaluacion.get(position).getValor()));
        holder.EditTextValorEvaluacion1.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public int getItemCount() {
        return listaEstudianteEvaluacion.size();
    }

    public class AddEvaluacionViewHolder extends RecyclerView.ViewHolder {
        TextView idenEstu1, txtNombre1, txtCI1, txtSexo1;
        TextInputEditText EditTextValorEvaluacion1;
        public AddEvaluacionViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            idenEstu1 = itemView.findViewById(R.id.idenEstu1);
            txtNombre1 = itemView.findViewById(R.id.txtNombre1);
            txtCI1 = itemView.findViewById(R.id.txtCI1);
            txtSexo1 = itemView.findViewById(R.id.txtSexo1);
            EditTextValorEvaluacion1 = itemView.findViewById(R.id.EditTextValorEvaluacion1);
        }

    }
}
