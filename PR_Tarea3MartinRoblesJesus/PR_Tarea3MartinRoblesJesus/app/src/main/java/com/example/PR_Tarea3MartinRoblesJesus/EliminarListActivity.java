package com.example.PR_Tarea3MartinRoblesJesus;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PR_Tarea3MartinRoblesJesus.models.BaseDatos;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.util.ArrayList;
import java.util.Objects;

public class EliminarListActivity extends AppCompatActivity {

    private EliminarAdapter listaAdapter;
    private RecyclerView rv;
    private BaseDatos db;
    private ArrayList<Producto> arrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        db = new BaseDatos(getApplicationContext());

        Toolbar toolbarEliminar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarEliminar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Eliminar un producto");

        inicializarRecyclerView();
        getAllProducts();
    }

    private void inicializarRecyclerView() {

        rv = findViewById(R.id.rvList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        // Línea entre filas
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
    }

    private void getAllProducts() {

        Cursor c = db.getProductos(false);
        arrayProductos = new ArrayList<>();

        if (c.getCount() > 0) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                double price = c.getDouble(5);
                int avilable = c.getInt(6);
                String ingredientes = c.getString(2);
                int gramos = c.getInt(4);
                String url = c.getString(3);
                arrayProductos.add(new Producto(id, name, price, avilable, gramos, ingredientes, url));
                c.moveToNext();
            }

            c.close();
            db.close();
        }
        listaAdapter = new EliminarAdapter(arrayProductos);
        rv.setAdapter(listaAdapter);
    }
}