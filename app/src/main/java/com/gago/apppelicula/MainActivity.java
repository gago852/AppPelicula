package com.gago.apppelicula;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    ArrayList<Pelicula> peliculaArrayList;
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
        radioGroup=findViewById(R.id.radioGroup);
        btGuardar = findViewById(R.id.idBtGuardar);
        btCancelar = findViewById(R.id.idBtCancelar);
        spinnerGeneros = findViewById(R.id.idSpinnerGeneros);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.spinner_generos, R.layout.support_simple_spinner_dropdown_item);
        spinnerGeneros.setAdapter(adapter);
        spinnerGeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                genero=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        peliculaArrayList = new ArrayList<>();
        btGuardar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idBtGuardar:
                String idioma;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.idRbEspanol:
                        idioma=getString(R.string.espanol);
                        break;
                    case R.id.idRbIngles:
                        idioma=getString(R.string.ingles);
                        break;
                    default:
                        idioma=getString(R.string.espanol);
                        break;
                }
                    Pelicula pelicula=new Pelicula(edNombre.getText().toString(),edDirector.getText().toString(),idioma,genero);
                    peliculaArrayList.add(pelicula);
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
                Toast.makeText(getApplicationContext(), "mayuscula", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_listado:
                Intent i = new Intent(this, ListadoActivity.class);

                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
