package com.gago.apppelicula;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolderPelicula> {

    private ArrayList<Pelicula> listaPeliculas;

    public PeliculaAdapter(ArrayList<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public PeliculaAdapter.ViewHolderPelicula onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pelicula_layout, null, false);
        return new ViewHolderPelicula(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaAdapter.ViewHolderPelicula holder, int position) {
        holder.txtNombre.setText(listaPeliculas.get(position).getNombre());
        holder.txtDirector.setText(listaPeliculas.get(position).getDirector());
        holder.txtGenero.setText(listaPeliculas.get(position).getGenero());
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public class ViewHolderPelicula extends RecyclerView.ViewHolder {

        TextView txtNombre, txtDirector, txtGenero;

        public ViewHolderPelicula(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.idtxtNombre);
            txtDirector = itemView.findViewById(R.id.idtxtDirector);
            txtGenero = itemView.findViewById(R.id.idtxtGenero);
        }
    }
}
