package com.example.PR_Tarea3MartinRoblesJesus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PR_Tarea3MartinRoblesJesus.models.BaseDatos;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.util.ArrayList;
import java.util.Objects;

public class ListaActivity extends AppCompatActivity {
    Context context = this;
    private ListaAdapter listaAdapter;
    private RecyclerView rv;
    private BaseDatos db;
    private ArrayList<Producto> arrayProductos;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        db = new BaseDatos(getApplicationContext());

        Toolbar toolbarListado = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarListado);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Listado de productos");

    }

    @Override
    protected void onStart() {
        super.onStart();
        inicializarRecyclerView();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean soloDisponibles = sp.getBoolean("Solo_disponibles", false);

        getAllProducts(soloDisponibles);


        listaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = rv.getChildAdapterPosition(v);
                Log.e("posicion", String.valueOf(posicion));
            }
        });

        if (db.countProducts() == 0)
            Toast.makeText(getApplicationContext(), "No hay productos. Añada alguno", Toast.LENGTH_SHORT).show();
    }
 private void inicializarRecyclerView() {

        rv = findViewById(R.id.rvList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);
    }



    private void getAllProducts(boolean isAvilable) {

        Cursor c = db.getProductos(isAvilable);
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
                String url =  c.getString(3);
                arrayProductos.add(new Producto(id,name,price,avilable,gramos,ingredientes,url));
                c.moveToNext();
            }

            c.close();
            db.close();
        }
        listaAdapter = new ListaAdapter(context, arrayProductos);
        rv.setAdapter(listaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuPreferencias) {
            startActivity(new Intent(this, Preferencias.class));
            return true;
        } else if (itemId ==R.id.menuInsertarProducto) {
            startActivity(new Intent(this, InsertActivity.class));
            return true;
        }else if(itemId ==R.id.menuEliminarProducto){
            startActivity(new Intent(this, EliminarListActivity.class));
            return true;
        }
        return false;
    }
}
