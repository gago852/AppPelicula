package com.gago.apppelicula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    ArrayList<Pelicula> listaPeliculas;
    RecyclerView recyclerViewPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listaPeliculas=new ArrayList<>();
        recyclerViewPeliculas=findViewById(R.id.idRcPeliculas);
        recyclerViewPeliculas.setLayoutManager(new LinearLayoutManager(this));

        listaPeliculas.add(new Pelicula("nombre","director","idioma","genero"));
        PeliculaAdapter peliculaAdapter=new PeliculaAdapter(listaPeliculas);
        recyclerViewPeliculas.setAdapter(peliculaAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_ordenar_genero:
                Toast.makeText(getApplicationContext(), "genero", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_orderar_nombre:
                Toast.makeText(getApplicationContext(), "nombre", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_invertir:
                Toast.makeText(getApplicationContext(), "invertir", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_eliminar_primero:
                Toast.makeText(getApplicationContext(), "eliminar", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
