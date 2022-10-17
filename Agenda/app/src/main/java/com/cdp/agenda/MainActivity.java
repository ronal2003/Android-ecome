package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.cdp.agenda.adaptadores.ListaClienteAdapter;
import com.cdp.agenda.db.DbCliente;
import com.cdp.agenda.entidades.CLiente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView txtBuscar;
    RecyclerView listaclientes;
    ArrayList<CLiente> listaArrayclientes;
    FloatingActionButton fabNuevo;
    ListaClienteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaclientes = findViewById(R.id.listaclientes);
        fabNuevo = findViewById(R.id.favNuevo);

        listaclientes.setLayoutManager(new LinearLayoutManager(this));
        cargarLista();

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });


    }

    public void cargarLista() {
        DbCliente dbclientes = new DbCliente(MainActivity.this);
        listaArrayclientes = new ArrayList<>();

        adapter = new ListaClienteAdapter(dbclientes.mostrarclientes());
        listaclientes.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.menuSalir:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivityForResult(intent, 0);
    }

    private void Modificar() {
        Intent intent = new Intent(this, modificar.class);
        startActivity(intent);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cargarLista();
        super.onActivityResult(requestCode, resultCode, data);

    }

}