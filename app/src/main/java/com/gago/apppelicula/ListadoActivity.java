package com.gago.apppelicula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListadoActivity extends AppCompatActivity {

    ArrayList<Pelicula> listaPeliculas;
    RecyclerView recyclerViewPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listaPeliculas = new ArrayList<>();
        recyclerViewPeliculas = findViewById(R.id.idRcPeliculas);
        recyclerViewPeliculas.setLayoutManager(new LinearLayoutManager(this));
        Intent i = getIntent();
        listaPeliculas = i.getParcelableArrayListExtra("pelis");

        PeliculaAdapter peliculaAdapter = new PeliculaAdapter(listaPeliculas);
        recyclerViewPeliculas.setAdapter(peliculaAdapter);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView txtnombre;
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.i("test", menuInfo.toString());
        switch (item.getItemId()) {
            case 1:
                /*txtnombre = findViewById(R.id.idtxtNombre);
                txtnombre.setTextColor(Color.RED);
                Log.i("test", menuInfo.position + "");*/
            case 2:
                txtnombre = findViewById(R.id.idtxtNombre);
                txtnombre.setTextColor(Color.BLUE);
            case 3:
                txtnombre = findViewById(R.id.idtxtNombre);
                txtnombre.setTextColor(Color.GREEN);
            case 4:
                txtnombre = findViewById(R.id.idtxtNombre);
                txtnombre.setTextColor(Color.YELLOW);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        PeliculaAdapter peliculaAdapter;

        switch (id) {
            case R.id.action_ordenar_genero:
                Collections.sort(listaPeliculas, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getGenero().compareTo(o2.getGenero()) < 0) {
                            return -1;
                        }
                        if (o1.getGenero().compareTo(o2.getGenero()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new PeliculaAdapter(listaPeliculas);
                recyclerViewPeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_orderar_nombre:
                Collections.sort(listaPeliculas, new Comparator<Pelicula>() {
                    @Override
                    public int compare(Pelicula o1, Pelicula o2) {
                        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
                            return -1;
                        }
                        if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                peliculaAdapter = new PeliculaAdapter(listaPeliculas);
                recyclerViewPeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_invertir:
                Collections.reverse(listaPeliculas);
                peliculaAdapter = new PeliculaAdapter(listaPeliculas);
                recyclerViewPeliculas.setAdapter(peliculaAdapter);
                break;
            case R.id.action_eliminar_primero:
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoActivity.this);
                builder.setMessage("Desea eliminar una pelicula?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                listaPeliculas.remove(0);
                                PeliculaAdapter peliculaAdapter = new PeliculaAdapter(listaPeliculas);
                                recyclerViewPeliculas.setAdapter(peliculaAdapter);
                                Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("pelis", listaPeliculas);
                setResult(Activity.RESULT_OK, i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
