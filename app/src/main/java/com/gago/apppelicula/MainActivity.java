package com.gago.apppelicula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextInputEditText edNombre, edDirector;
    RadioButton rbEspanol, rbIngles;
    RadioGroup radioGroup;
    Button btGuardar, btCancelar;
    Spinner spinnerGeneros;
    ArrayList<Pelicula> peliculaArrayList = new ArrayList<>();
    String genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        edNombre = findViewById(R.id.idEdNombrePelicula);
        edDirector = findViewById(R.id.idEdDirector);
        rbEspanol = findViewById(R.id.idRbEspanol);
        rbIngles = findViewById(R.id.idRbIngles);
        radioGroup = findViewById(R.id.radioGroup);
        btGuardar = findViewById(R.id.idBtGuardar);
        btCancelar = findViewById(R.id.idBtCancelar);
        spinnerGeneros = findViewById(R.id.idSpinnerGeneros);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.spinner_generos, R.layout.support_simple_spinner_dropdown_item);
        spinnerGeneros.setAdapter(adapter);
        spinnerGeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genero = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btGuardar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtGuardar:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Desea agregar esta pelicula?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idioma;
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.idRbEspanol:
                                idioma = getString(R.string.espanol);
                                break;
                            case R.id.idRbIngles:
                                idioma = getString(R.string.ingles);
                                break;
                            default:
                                idioma = getString(R.string.espanol);
                                break;
                        }
                        Pelicula pelicula = new Pelicula(edNombre.getText().toString(), edDirector.getText().toString(), idioma, genero);
                        peliculaArrayList.add(pelicula);
                        edNombre.setText("");
                        edDirector.setText("");
                        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;
            case R.id.idBtCancelar:
                edNombre.setText("");
                edDirector.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_mayuscula:
                edNombre.setText(edNombre.getText().toString().toUpperCase());
                edDirector.setText(edDirector.getText().toString().toUpperCase());
                break;
            case R.id.action_listado:
                Intent i = new Intent(this, ListadoActivity.class);
                i.putParcelableArrayListExtra("pelis", peliculaArrayList);
                startActivityForResult(i, 5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            peliculaArrayList = data.getParcelableArrayListExtra("pelis");

        }

    }
}
